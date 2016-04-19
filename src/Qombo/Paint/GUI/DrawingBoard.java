/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.GUI;

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
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JComponent;

/**
 *
 * @author lo2ay
 */
public class DrawingBoard extends JComponent {
    
        ArrayList<Shape> shapes = new ArrayList<Shape>();
        ArrayList<Color> shapeFill = new ArrayList<Color>();
        ArrayList<Color> shapeStroke = new ArrayList<Color>();
        Point drawStart, drawEnd;
        MainGUI gui;

        public DrawingBoard(MainGUI gui) {
            this.gui = gui;
            this.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    drawStart = new Point(e.getX(), e.getY());
                    drawEnd = drawStart;
                    repaint();

                }

                public void mouseReleased(MouseEvent e) {
                    if (gui.currentAction == 1) {
                        Shape shape = drawRectangle(drawStart.x, drawStart.y, e.getX(), e.getY());
                        shapes.add(shape);
                    } else if (gui.currentAction == 2) {
                        Shape shape = drawEllipse(drawStart.x, drawStart.y, e.getX(), e.getY());
                        shapes.add(shape);
                    }
                    else if (gui.currentAction == 0) {
                        Shape shape = drawLine(drawStart.x, drawStart.y, e.getX(), e.getY());
                        shapes.add(shape);
                    }

                    shapeFill.add(gui.fillColor);
                    shapeStroke.add(gui.strokeColor);
                    drawStart = null;
                    drawEnd = null;

                    repaint();

                }
            }); // end of addMouseListener
            this.addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent e) {
                    drawEnd = new Point(e.getX(), e.getY());

                    repaint();
                }
            }); // end of addMouseMotionListener
        } // end of constructor

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
                if (gui.currentAction == 1) {
                    shape = drawRectangle(drawStart.x, drawStart.y, drawEnd.x, drawEnd.y);
                    graphicsSettings.draw(shape);
                } else if (gui.currentAction == 2) {
                    shape = drawEllipse(drawStart.x, drawStart.y, drawEnd.x, drawEnd.y);
                    graphicsSettings.draw(shape);
                }
                if (gui.currentAction == 1) {
                    shape = drawLine(drawStart.x, drawStart.y, drawEnd.x, drawEnd.y);
                    graphicsSettings.draw(shape);
                }
            }
        }

        private Rectangle2D.Float drawRectangle(int x1, int y1, int x2, int y2) {
            int x = Math.min(x1, x2);
            int y = Math.min(y1, y2);

            int width = Math.abs(x1 - x2);
            int height = Math.abs(y1 - y2);
            return new Rectangle2D.Float(x, y, width, height);

        }

        private Ellipse2D.Float drawEllipse(int x1, int y1, int x2, int y2) {
            int x = Math.min(x1, x2);
            int y = Math.min(y1, y2);

            int width = Math.abs(x1 - x2);
            int height = Math.abs(y1 - y2);
            return new Ellipse2D.Float(x, y, width, height);
        }
        private Line2D.Float drawLine(int x1,int y1, int x2,int y2)
        {
            return new Line2D.Float(x1,y1,x2,y2);
        }

}
