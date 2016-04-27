/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.GUI;

import Qombo.Logging.Logging;
import Qombo.Paint.Shapes.ShapeFactory;
import static Qombo.Paint.Shapes.ShapeFactory.ShapeType.*;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JComponent;
import javax.swing.undo.UndoManager;

/**
 *
 * @author lo2ay
 */
public class DrawingBoard extends JComponent implements Logging{

    public static ArrayList<Shape> shapes = new ArrayList();
    public static ArrayList<Shape> oldShapes = new ArrayList();

    private ArrayList<Color> shapeFill = new ArrayList();
    private ArrayList<Color> shapeStroke = new ArrayList();
    private Point drawStart, drawEnd;
    private final MainGUI gui;
    private int triangleClicks = 0;
    private Point[] triangleVertices = new Point[3];
    private ShapeFactory shapeFactory = ShapeFactory.getFactory();

    public DrawingBoard(MainGUI gui) {
        super();
        shapes = new ArrayList();
        oldShapes = new ArrayList();
        this.gui = gui;
        this.setBackground(Color.white);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                if (gui.currentAction != 11) {
                    drawStart = new Point(e.getX(), e.getY());
                    drawEnd = null;
                    repaint();
                    log("Mouse pressed.");
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                if (gui.currentAction != 11) {
                    switch (gui.currentAction) {
                        case 1: {
                            Shape shape = shapeFactory.drawShape(drawStart, e.getPoint(), RECTANGLE);
                            shapes.add(shape);
                            break;
                        }
                        case 2: {
                            Shape shape = shapeFactory.drawShape(drawStart, e.getPoint(), ELLIPSE);
                            shapes.add(shape);
                            break;
                        }
                        case 3: {
                            Shape shape = shapeFactory.drawShape(drawStart, e.getPoint(), LINE);
                            shapes.add(shape);

                            break;
                        }
                        case 4: {
                            Shape shape = shapeFactory.drawShape(drawStart, e.getPoint(), CIRCLE);
                            shapes.add(shape);
                            break;
                        }
                        case 5: {
                            Shape shape = shapeFactory.drawShape(drawStart, e.getPoint(), SQUARE);
                            shapes.add(shape);
                            break;
                        }
                        default:
                            break;
                    }
                }
                shapeFill.add(gui.fillColor);
                shapeStroke.add(gui.strokeColor);
                drawStart = null;
                drawEnd = null;
                repaint();
                log("Mouse released.");
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                log ("Mouse clicked.");
                if (gui.currentAction == 6) {
                    triangleVertices[triangleClicks++] = e.getPoint();
                    log("vertex #" + triangleClicks + " registered.");
                    if (triangleClicks == 3) {
                        triangleClicks = 0;
                        Shape shape = shapeFactory.drawShape(triangleVertices);
                        log("Triangle registered");
                        shapes.add(shape);
                        log("Triangle added to array list.");
                        repaint();
                        log("Triangle painted.");
                    }
                } else if (gui.currentAction == 7) {
                    Shape shapeToDelete = null;
                    for (int i = shapes.size() - 1; i >= 0; i--) {
                        if (shapes.get(i).contains(e.getPoint())) {
                            shapeToDelete = shapes.get(i);
                            log("Shape to delete found");
                            break;
                        }
                    }
                    if (shapeToDelete != null){
                        shapes.remove(shapeToDelete);
                    }
                    repaint();
                }
            }
        }); // end of addMouseListener
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (gui.currentAction == 11) {
                    int x = e.getX();
                    int y = e.getY();

                    Shape shape = null;
                    gui.strokeColor = gui.fillColor;
                    shape = shapeFactory.drawBrush(x, y, 5, 5);
                    shapes.add(shape);
                    shapeFill.add(gui.fillColor);
                    shapeStroke.add(gui.strokeColor);
                }
                drawEnd = new Point(e.getX(), e.getY());
                repaint();
            }
        }); // end of addMouseMotionListener
        
    } // end of constructor

    @Override
    public void paint(Graphics g) {

        Graphics2D graphicsSettings = (Graphics2D) g;
        graphicsSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphicsSettings.setStroke(new BasicStroke(2));
        Iterator<Color> strokeCounter = shapeStroke.iterator();
        Iterator<Color> fillCounter = shapeFill.iterator();

        
        for (Shape s : shapes) {
            graphicsSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            graphicsSettings.setPaint(strokeCounter.next());
            graphicsSettings.draw(s);
            graphicsSettings.setPaint(fillCounter.next());
            graphicsSettings.fill(s);
        }
        if (drawStart != null && drawEnd != null) {
            graphicsSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.40f));
            graphicsSettings.setPaint(Color.lightGray);
            Shape shape = null;
            switch (gui.currentAction) {
                case 1:
                    shape = shapeFactory.drawShape(drawStart, drawEnd, RECTANGLE);
                    graphicsSettings.draw(shape);
                    break;
                case 2:
                    shape = shapeFactory.drawShape(drawStart, drawEnd, ELLIPSE);
                    graphicsSettings.draw(shape);
                    break;
                case 3:
                    shape = shapeFactory.drawShape(drawStart, drawEnd, LINE);
                    graphicsSettings.draw(shape);
                    break;
                case 4:
                    shape = shapeFactory.drawShape(drawStart, drawEnd, CIRCLE);
                    graphicsSettings.draw(shape);
                    break;
                case 5:
                    shape = shapeFactory.drawShape(drawStart, drawEnd, SQUARE);
                    graphicsSettings.draw(shape);
                    break;
                case 6:
                    if (triangleClicks == 3) {
                        shape = shapeFactory.drawShape(triangleVertices);
                        graphicsSettings.draw(shape);
                    }
                    break;
                default:
                    shape = null;
            }

        }
    }    
}
