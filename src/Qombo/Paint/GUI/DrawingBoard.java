/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.GUI;

import Qombo.Paint.Shapes.*;
import Qombo.Paint.Shapes.Circle;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;

/**
 *
 * @author lo2ay
 */
public class DrawingBoard extends JPanel {

    private void log(String str) {
        System.out.println(str);
    }

    ArrayList<Shape> shapes = new ArrayList();
    ArrayList<Color> shapeFill = new ArrayList();
    ArrayList<Color> shapeStroke = new ArrayList();
    Point drawStart, drawEnd;
    MainGUI gui;

    public DrawingBoard(MainGUI gui) {
        this.gui = gui;
        this.setBackground(Color.white);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawStart = new Point(e.getX(), e.getY());
                drawEnd = drawStart;
                repaint();
                log("Mouse pressed.");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                switch (gui.currentAction) {
                    case 1:
                        {
                            Shape shape = drawRectangle(drawStart.x, drawStart.y, e.getX(), e.getY());
                            shapes.add(shape);
                            break;
                        }
                    case 2:
                        {
                            Shape shape = drawEllipse(drawStart.x, drawStart.y, e.getX(), e.getY());
                            shapes.add(shape);
                            break;
                        }
                    case 3:
                        {
                            Shape shape = drawLine(drawStart.x, drawStart.y, e.getX(), e.getY());
                            shapes.add(shape);
                            break;
                        }
                        case 4:
                        {
                            Shape shape = drawCircle(drawStart.x, drawStart.y, e.getX(), e.getY());
                            shapes.add(shape);
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
                    shape = drawLine(drawStart.x, drawStart.y, drawEnd.x, drawEnd.y);
                    graphicsSettings.draw(shape);
                    break;
                case 4:
                    shape = drawCircle(drawStart.x, drawStart.y, drawEnd.x, drawEnd.y);
                    graphicsSettings.draw(shape);
                    break;
                default:
                    break;
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

    private Ellipse drawEllipse(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);

        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);
        return new Ellipse(x, y, width, height);
    }

    private Line drawLine(int x1, int y1, int x2, int y2) {
        return new Qombo.Paint.Shapes.Line(x1, y1, x2, y2);
    }
    
    private Circle drawCircle(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);

        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);
        int radius = Math.max(width, height);
        return new Circle(x, y, radius);
    }

}
