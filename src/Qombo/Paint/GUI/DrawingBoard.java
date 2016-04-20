/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.GUI;

import Qombo.Logging.Logging;
import Qombo.Paint.Shapes.Circle;
import Qombo.Paint.Shapes.Ellipse;
import Qombo.Paint.Shapes.Line;
import Qombo.Paint.Shapes.Rectangle;
import Qombo.Paint.Shapes.Square;
import Qombo.Paint.Shapes.Triangle;
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

/**
 *
 * @author lo2ay
 */
public class DrawingBoard extends JComponent implements Logging {

    public static final ArrayList<Shape> shapes = new ArrayList();
    private ArrayList<Color> shapeFill = new ArrayList();
    private ArrayList<Color> shapeStroke = new ArrayList();
    private Point drawStart, drawEnd;
    private MainGUI gui;
    private int triangleClicks = 0;
    private Point[] triangleVertices = new Point[3];

    public DrawingBoard(MainGUI gui) {
        this.gui = gui;
        this.setBackground(Color.white);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawStart = new Point(e.getX(), e.getY());
                drawEnd = null;
                repaint();
                log("Mouse pressed.");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                switch (gui.currentAction) {
                    case 1: {
                        Rectangle rectangle = drawRectangle(drawStart.x, drawStart.y, e.getX(), e.getY());
                        shapes.add(rectangle);
                        break;
                    }
                    case 2: {
                        Ellipse ellipse = drawEllipse(drawStart.x, drawStart.y, e.getX(), e.getY());
                        shapes.add(ellipse);
                        break;
                    }
                    case 3: {
                        Line line = drawLine(drawStart, e.getPoint());
                        shapes.add(line);

                        break;
                    }
                    case 4: {
                        Circle circle = drawCircle(drawStart.x, drawStart.y, e.getX(), e.getY());
                        shapes.add(circle);
                        break;
                    }
                    case 5: {
                        Square square = drawSquare(drawStart.x, drawStart.y, e.getX(), e.getY());
                        shapes.add(square);
                        break;
                    }
                    default:
                        break;
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
                if (gui.currentAction == 6) {
                    triangleVertices[triangleClicks++] = e.getPoint();
                    log("vertex #" + triangleClicks + " registered.");
                    if (triangleClicks == 3) {
                        triangleClicks = 0;
                        Triangle triangle = drawTriangle(triangleVertices);
                        log("Triangle registered");
                        shapes.add(triangle);
                        log("Triangle added to array list.");
                        repaint();
                        log("Triangle painted.");
                    }
                }
            }
        }); // end of addMouseListener
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
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

        graphicsSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        for (Shape s : shapes) {
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
                    shape = drawRectangle(drawStart.x, drawStart.y, drawEnd.x, drawEnd.y);
                    graphicsSettings.draw(shape);
                    break;
                case 2:
                    shape = drawEllipse(drawStart.x, drawStart.y, drawEnd.x, drawEnd.y);
                    graphicsSettings.draw(shape);
                    break;
                case 3:
                    shape = drawLine(drawStart, drawEnd);
                    graphicsSettings.draw(shape);
                    break;
                case 4:
                    shape = drawCircle(drawStart.x, drawStart.y, drawEnd.x, drawEnd.y);
                    graphicsSettings.draw(shape);
                    break;
                case 5:
                    shape = drawSquare(drawStart.x, drawStart.y, drawEnd.x, drawEnd.y);
                    graphicsSettings.draw(shape);
                    break;
                case 6:
                    if (triangleClicks == 3) {
                        shape = drawTriangle(triangleVertices);
                        graphicsSettings.draw(shape);
                    }
                    break;
                default:
                    shape = null;
            }

        }
    }

    private Rectangle drawRectangle(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);

        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);
        return new Rectangle(x, y, width, height);

    }

    private Square drawSquare(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);

        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);
        int side = Math.max(width, height);
        return new Square(x, y, side);

    }

    private Ellipse drawEllipse(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);

        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);
        return new Ellipse(x, y, width, height);
    }

    private Line drawLine(Point p1, Point p2) {
        return new Line(p1, p2);
    }

    private Circle drawCircle(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);

        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);
        int radius = Math.max(width, height);
        return new Circle(x, y, radius);
    }

    private Triangle drawTriangle(Point[] v) {
        int[] x = new int[]{(int) v[0].getX(), (int) v[1].getX(), (int) v[2].getX()};
        int[] y = new int[]{(int) v[0].getY(), (int) v[1].getY(), (int) v[2].getY()};
        return new Triangle(x, y);
    }

}
