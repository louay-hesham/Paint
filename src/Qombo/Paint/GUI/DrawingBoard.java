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
import java.util.ArrayList;
import java.util.List;
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
    public List<String> hist = new ArrayList();
    private Point drawStart, drawEnd;
    private final MainGUI gui;
    private int triangleClicks = 0;
    private boolean rotateDone = false;

    private Cursor moveCursor = new Cursor(Cursor.MOVE_CURSOR);
    private Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);

    public int getTriangleClicks() {
        return triangleClicks;
    }
    private Point[] triangleVertices = new Point[3];
    private ShapeFactory shapeFactory = ShapeFactory.getFactory();
    private Shape shapeToCopyOrMove = null;
    private Shape shapeToRotate = null;
    private Shape shapeToResize = null;
    private double angleOfRotation;
    private Point selectedShapeCenter;

    public DrawingBoard(MainGUI gui) {
        super();
        this.shapes = new ShapeArrayList();
        this.undoHistory = new Stack();
        this.redoHistory = new Stack();
        this.gui = gui;
        this.setBackground(Color.white);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawStart = e.getPoint();
                drawEnd = null;
                repaint();
                switch (gui.currentAction) {
                    case 9:
                        shapeToCopyOrMove = getSelectedShape(drawStart);
                        if (shapeToCopyOrMove != null) {
                            registerUserAction();
                            hist.add("Shape Moved");
                        }
                        break;
                    case 10:
                        shapeToCopyOrMove = getSelectedShape(drawStart).clone();
                        if (shapeToCopyOrMove != null) {
                            registerUserAction();
                            shapes.add(shapeToCopyOrMove);
                            hist.add("Shape Copied");
                        }
                        break;
                    case 11:
                        if (rotateDone) {
                            shapeToRotate = null;
                            rotateDone = false;
                        }
                        shapeToRotate = getSelectedShape(drawStart);
                        rotateDone = false;
                        if (shapeToRotate != null) {
                            selectedShapeCenter = shapeToRotate.getCenter();
                            registerUserAction();
                            hist.add("Shape Rotated");
                        }
                        break;
                    case 12:
                        shapeToResize = getSelectedShape(drawStart);
                        if (shapeToResize != null) {
                            registerUserAction();
                            hist.add("Shape Resized");
                        }
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
                        hist.add("Rectangle Drawn");
                        break;
                    }
                    case 2: {
                        shape = shapeFactory.getShape(drawStart, e.getPoint(), ELLIPSE);
                        registerUserAction();
                        hist.add("Ellipse Drawn");
                        break;
                    }
                    case 3: {
                        shape = shapeFactory.getShape(drawStart, e.getPoint(), LINE);
                        registerUserAction();
                        hist.add("Line Drawn");
                        break;
                    }
                    case 4: {
                        shape = shapeFactory.getShape(drawStart, e.getPoint(), CIRCLE);
                        registerUserAction();
                        hist.add("Circle Drawn");
                        break;
                    }
                    case 5: {
                        shape = shapeFactory.getShape(drawStart, e.getPoint(), SQUARE);
                        registerUserAction();
                        hist.add("Square Drawn");
                        break;
                    }
                    case 11:
                        rotateDone = true;
                        break;
                    default:
                        break;
                }
                if (shape != null) {
                    shapes.add(shape);
                }
                drawStart = null;
                drawEnd = null;
                repaint();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                switch (gui.currentAction) {
                    case 6:
                        triangleVertices[triangleClicks++] = e.getPoint();
                        gui.helperLabel.setText("Click three times on the canvas. Current click is #" + (triangleClicks == 3 ? 1 : (triangleClicks + 1)));
                        if (triangleClicks == 3) {
                            triangleClicks = 0;
                            Shape shape = shapeFactory.getShape(triangleVertices);
                            registerUserAction();
                            shapes.add(shape);
                            repaint();
                            hist.add("Triangle Drawn");
                        }
                        break;
                    case 7:
                        Shape shapeToDelete = getSelectedShape(e.getPoint());
                        if (shapeToDelete != null) {
                            registerUserAction();
                            shapes.remove(shapeToDelete);
                            hist.add("Shape Deleted");
                        }
                        repaint();
                        break;
                    case 8:
                        Shape shapeToColor = getSelectedShape(e.getPoint());
                        registerUserAction();
                        shapeToColor.setColor();
                        hist.add("Shape Re-Filled");
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
                    angleOfRotation = Math.atan2(e.getY() - selectedShapeCenter.getY(), e.getX() - selectedShapeCenter.getX());
                    rotateDone = false;
                } else if ((gui.currentAction == 12) && shapeToResize != null) {
                    shapeToResize.resize(e.getPoint());
                } else {
                    drawEnd = e.getPoint();
                }

                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                Shape shape = null;
                shape = getSelectedShape(e.getLocationOnScreen());
                if ((gui.currentAction == 9 || gui.currentAction == 10 || gui.currentAction == 11 || gui.currentAction == 12) && (shape != null)) {
                    setCursor(moveCursor);
                    shape = null;
                } else {
                    setCursor(defaultCursor);
                }

            }
        }); // end of addMouseMotionListener
        repaint();
    } // end of constructor

    private void registerUserAction() {
        log(undoHistory.size());
        undoHistory.push(shapes.clone());
        redoHistory.clear();
    }

    private Shape getSelectedShape(Point p) {
        Shape selectedShape = null;
        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (shapes.get(i) != null && ((Shape) shapes.get(i)).contains(p)) {
                selectedShape = (Shape) shapes.get(i);
                break;
            }
        }
        return selectedShape;
    }

    @Override
    public void paint(Graphics g) {
        for (Object s : shapes) {
            try {
                ((Shape) s).draw(g);
            } catch (NullPointerException n) {
            }
        }
        if (gui.currentAction == 11 && shapeToRotate != null) {
            shapeToRotate.rotate(g, angleOfRotation);
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
