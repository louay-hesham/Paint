/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.GUI;

import Qombo.Logging.Logging;
import Qombo.Paint.Core.ShapeArrayList;
import Qombo.Paint.Shapes.Shape;
import Qombo.Paint.Shapes.ShapeFactory;
import static Qombo.Paint.Shapes.ShapeFactory.ShapeType.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.util.Stack;
import javax.swing.JComponent;

/**
 *
 * @author lo2ay
 */
public class DrawingBoard extends JComponent implements Logging {

    protected ShapeArrayList<Shape> shapes = new ShapeArrayList();

    protected Stack<ShapeArrayList<Shape>> undoHistory = new Stack();
    protected Stack<ShapeArrayList<Shape>> redoHistory = new Stack();

    private Point drawStart, drawEnd;
    private final MainGUI gui;
    private int triangleClicks = 0;

    public int getTriangleClicks() {
        return triangleClicks;
    }
    private Point[] triangleVertices = new Point[3];
    private ShapeFactory shapeFactory = ShapeFactory.getFactory();
    private Shape shapeToCopyOrMove = null;
    private Shape shapeToRotate = null;
    private double startSlope;
    private double angleOfRotation;
    private Point selectedShapeCenter;

    public DrawingBoard(MainGUI gui) {
        super();
        this.shapes = new ShapeArrayList();
        this.undoHistory = new Stack();
        this.redoHistory = new Stack();
        this.gui = gui;
        this.setBackground(Color.white);
        log(undoHistory.size());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                drawStart = e.getPoint();
                drawEnd = null;
                repaint();
                log("Mouse pressed.");
                switch (gui.currentAction) {
                    case 9:
                        shapeToCopyOrMove = getSelectedShape(drawStart);
                        if (shapeToCopyOrMove != null) {
                            registerUserAction();
                        }
                        break;
                    case 10:
                        shapeToCopyOrMove = getSelectedShape(drawStart).clone();
                        if (shapeToCopyOrMove != null) {
                            registerUserAction();
                            shapes.add(shapeToCopyOrMove);
                        }
                        break;
                    case 11:
                        shapeToRotate = getSelectedShape(drawStart);
                        if (shapeToRotate != null) {
                            selectedShapeCenter = shapeToRotate.getCenter();
                            startSlope = (e.getY() - selectedShapeCenter.getY()) / (e.getX() - selectedShapeCenter.getX());
                            registerUserAction();
                        }
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Shape shape = null;
                switch (gui.currentAction) {
                    case 1: {
                        shape = shapeFactory.getShape(drawStart, e.getPoint(), RECTANGLE);
                        registerUserAction();
                        break;
                    }
                    case 2: {
                        shape = shapeFactory.getShape(drawStart, e.getPoint(), ELLIPSE);
                        registerUserAction();
                        break;
                    }
                    case 3: {
                        shape = shapeFactory.getShape(drawStart, e.getPoint(), LINE);
                        registerUserAction();
                        break;
                    }
                    case 4: {
                        shape = shapeFactory.getShape(drawStart, e.getPoint(), CIRCLE);
                        registerUserAction();
                        break;
                    }
                    case 5: {
                        shape = shapeFactory.getShape(drawStart, e.getPoint(), SQUARE);
                        registerUserAction();
                        break;
                    }
                    default:
                        break;
                }
                shapes.add(shape);
                drawStart = null;
                drawEnd = null;
                repaint();
                log("Mouse released.");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                log("Mouse clicked.");
                switch (gui.currentAction) {
                    case 6:
                        triangleVertices[triangleClicks++] = e.getPoint();
                        gui.helperLabel.setText("Click three times on the canvas. Current click is #" + (triangleClicks == 3 ? 1 : (triangleClicks + 1)));
                        log("vertex #" + triangleClicks + " registered.");
                        if (triangleClicks == 3) {
                            triangleClicks = 0;
                            Shape shape = shapeFactory.getShape(triangleVertices);
                            log("Triangle registered");
                            registerUserAction();
                            shapes.add(shape);
                            repaint();
                            log("Triangle painted.");
                        }
                        break;
                    case 7:
                        Shape shapeToDelete = getSelectedShape(e.getPoint());
                        if (shapeToDelete != null) {
                            registerUserAction();
                            shapes.remove(shapeToDelete);
                        }
                        repaint();
                        break;
                    case 8:
                        Shape shapeToColor = getSelectedShape(e.getPoint());
                        registerUserAction();
                        shapeToColor.setColor();
                        repaint();
                        break;
                    default:
                        break;
                }
            }
        }); // end of addMouseListener
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if ((gui.currentAction == 9 || gui.currentAction == 10) && shapeToCopyOrMove != null) {
                    shapeToCopyOrMove.setPosition(e.getPoint());
                } else if (gui.currentAction == 11 && shapeToRotate != null) {
                    double slope = (e.getY() - selectedShapeCenter.getY()) / (e.getX() - selectedShapeCenter.getX());
                    angleOfRotation = Math.atan(slope - startSlope);
                } else {
                    drawEnd = e.getPoint();
                }
                repaint();
            }
            
            @Override
            public void mouseMoved (MouseEvent e){
                Shape shape = getSelectedShape(e.getLocationOnScreen());
                Cursor moveCursor = new Cursor(Cursor.MOVE_CURSOR);
                Cursor defaultCursor = new Cursor (Cursor.DEFAULT_CURSOR);
                if((gui.currentAction == 9) && (shape == shapeToCopyOrMove) ){
                    System.out.println("this is mouse moved!");
                    setCursor(moveCursor);
                }
                else {
                    setCursor(defaultCursor);
                }
                
            }
        }); // end of addMouseMotionListener
        repaint();
        
       
    } // end of constructor

    private void registerUserAction() {
        undoHistory.push((ShapeArrayList<Shape>) shapes.clone());
        redoHistory.clear();
    }

    private Shape getSelectedShape(Point p) {
        Shape selectedShape = null;
        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (shapes.get(i) != null && ((Shape) shapes.get(i)).contains(p)) {
                selectedShape = (Shape) shapes.get(i);
                log(selectedShape.getClass() + " is at " + p);
                break;
            }
        }
        return selectedShape;
    }

    @Override
    public void paint(Graphics g) {
        if (gui.currentAction == 11 && shapeToRotate != null) {
            shapeToRotate.rotate(g, angleOfRotation);
            log("Angle is " + angleOfRotation);
        }
        for (Object s : shapes) {
            try {
                ((Shape) s).draw(g);
            } catch (NullPointerException n) {
            }
        }

        if (drawStart != null && drawEnd != null) {
            Shape shape = null;
            switch (gui.currentAction) {
                case 1:
                    shape = shapeFactory.getShape(drawStart, drawEnd, RECTANGLE);
                    break;
                case 2:
                    shape = shapeFactory.getShape(drawStart, drawEnd, ELLIPSE);
                    break;
                case 3:
                    shape = shapeFactory.getShape(drawStart, drawEnd, LINE);
                    break;
                case 4:
                    shape = shapeFactory.getShape(drawStart, drawEnd, CIRCLE);
                    break;
                case 5:
                    shape = shapeFactory.getShape(drawStart, drawEnd, SQUARE);
                    break;
                case 6:
                    if (triangleClicks == 3) {
                        shape = shapeFactory.getShape(triangleVertices);
                    }
                    break;
                default:
                    shape = null;
            }
            if (shape != null) {
                shape.draw(g);
            }
        }
    }
}
