/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.GUI;

import Qombo.Logging.Logging;
import Qombo.Paint.Shapes.Shape;
import Qombo.Paint.Shapes.ShapeFactory;
import static Qombo.Paint.Shapes.ShapeFactory.ShapeType.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JComponent;

/**
 *
 * @author lo2ay
 */
public class DrawingBoard extends JComponent implements Logging {

    public static ArrayList<Shape> shapes = new ArrayList();

    protected Stack<ArrayList<Shape>> undoHistory = new Stack();
    protected Stack<ArrayList<Shape>> redoHistory = new Stack();

    private Point drawStart, drawEnd;
    private final MainGUI gui;
    private int triangleClicks = 0;

    public int getTriangleClicks() {
        return triangleClicks;
    }
    private Point[] triangleVertices = new Point[3];
    private ShapeFactory shapeFactory = ShapeFactory.getFactory();

    public DrawingBoard(MainGUI gui) {
        super();
        shapes = new ArrayList();
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
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Shape shape = null;
                switch (gui.currentAction) {
                    case 1: {
                        shape = shapeFactory.getShape(drawStart, e.getPoint(), RECTANGLE);
                        break;
                    }
                    case 2: {
                        shape = shapeFactory.getShape(drawStart, e.getPoint(), ELLIPSE);
                        break;
                    }
                    case 3: {
                        shape = shapeFactory.getShape(drawStart, e.getPoint(), LINE);
                        break;
                    }
                    case 4: {
                        shape = shapeFactory.getShape(drawStart, e.getPoint(), CIRCLE);
                        break;
                    }
                    case 5: {
                        shape = shapeFactory.getShape(drawStart, e.getPoint(), SQUARE);
                        break;
                    }
                    default:
                        break;
                }
                undoHistory.push((ArrayList<Shape>) shapes.clone());
                shapes.add(shape);
                redoHistory.clear();
                drawStart = null;
                drawEnd = null;
                repaint();
                log("Mouse released.");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                log("Mouse clicked.");
                if (gui.currentAction == 6) {
                    triangleVertices[triangleClicks++] = e.getPoint();
                    gui.helperLabel.setText("Click three times on the canvas. Current click is #" + (triangleClicks == 3 ? 1 : (triangleClicks + 1)));
                    log("vertex #" + triangleClicks + " registered.");
                    if (triangleClicks == 3) {
                        triangleClicks = 0;
                        Shape shape = shapeFactory.getShape(triangleVertices);
                        log("Triangle registered");
                        undoHistory.push((ArrayList<Shape>) shapes.clone());
                        shapes.add(shape);
                        redoHistory.clear();
                        repaint();
                        log("Triangle painted.");
                    }
                } else if (gui.currentAction == 7) {
                    Shape shapeToDelete = getSelectedShape(e.getPoint());
                    if (shapeToDelete != null) {
                        undoHistory.push((ArrayList<Shape>) shapes.clone());
                        shapes.remove(shapeToDelete);
                        redoHistory.clear();
                    }
                    repaint();
                } else if (gui.currentAction == 10) {
                    Shape shapeToColor = getSelectedShape(e.getPoint());
                    undoHistory.push((ArrayList<Shape>) shapes.clone());
                    shapeToColor.setColor(MainGUI.getFillColor());
                    redoHistory.clear();
                    repaint();
                }
            }
        }); // end of addMouseListener
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                drawEnd = e.getPoint();
                repaint();
            }
        }); // end of addMouseMotionListener

    } // end of constructor

    private Shape getSelectedShape(Point p) {
        Shape shapeToDelete = null;
        for (int i = shapes.size() - 1; i >= 0; i--) {
            if (shapes.get(i)!=null && shapes.get(i).contains(p)) {
                shapeToDelete = shapes.get(i);
                log("Shape to delete found");
                break;
            }
        }
        return shapeToDelete;
    }

    @Override
    public void paint(Graphics g) {
        for (Shape s : shapes) {
            try {
                s.draw(g);
            } catch (NullPointerException n) {
                log(n.getCause());
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
