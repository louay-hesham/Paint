/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.Shapes;

import Qombo.Paint.GUI.CornerRectangles;
import Qombo.Paint.GUI.MainGUI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

/**
 *
 * @author lo2ay
 */
public class Triangle extends Polygon implements Shape {

    private Color fillColor, outlineColor;
    private Point center;
    private Path2D.Double rotatedShape = null;
    private Point2D[] points = new Point2D[3];
    public CornerRectangles corners;

    public Point2D[] getPoints() {
        return points;
    }

    public Triangle(int[] xpoints, int[] ypoints) {
        super(xpoints, ypoints, 3);
        this.fillColor = MainGUI.getFillColor();
        this.outlineColor = MainGUI.getOutlineColor();
        this.center = new Point((int) this.getBounds().getCenterX(), (int) this.getBounds().getCenterY());
        for (int i = 0; i < 3; i++) {
            this.points[i] = new Point.Double(xpoints[i], ypoints[i]);
        }
        corners = new CornerRectangles(this);
    }

    @Override
    public Point getCenter() {
        return this.center;
    }

    @Override
    public void draw(Graphics g) {
        java.awt.Shape shape = (this.rotatedShape == null ? this : rotatedShape);
        Graphics2D graphicsSettings = (Graphics2D) g;
        graphicsSettings.setStroke(new BasicStroke(2));
        graphicsSettings.setPaint(outlineColor);
        graphicsSettings.draw(shape);
        graphicsSettings.setPaint(fillColor);
        graphicsSettings.fill(shape);
    }

    @Override
    public void setColor() {
        this.fillColor = MainGUI.getFillColor();
        this.outlineColor = MainGUI.getOutlineColor();
    }

    @Override
    public Shape clone() {
        Triangle cloneTriangle = new Triangle(xpoints, ypoints);
        cloneTriangle.fillColor = this.fillColor;
        cloneTriangle.outlineColor = this.outlineColor;
        cloneTriangle.rotatedShape = this.rotatedShape == null ? null : (Path2D.Double) this.rotatedShape.clone();
        return cloneTriangle;
    }

    @Override
    public void setPosition(Point2D p) {
        if (this.rotatedShape != null) {
            AffineTransform transform = new AffineTransform();
            transform.translate(p.getX() - center.getX(), p.getY() - center.getY());
            this.rotatedShape.transform(transform);
        }
        int xCor = (int) this.getBounds2D().getCenterX();
        int yCor = (int) this.getBounds2D().getCenterY();
        this.translate((int) p.getX() - xCor, (int) p.getY() - yCor);
        this.center = new Point((int) this.getBounds().getCenterX(), (int) this.getBounds().getCenterY());
        for (int i = 0; i < 3; i++) {
            this.points[i] = new Point.Double(xpoints[i], ypoints[i]);
        }
    }

    @Override
    public boolean contains(Point2D pd) {
        return this.rotatedShape == null ? super.contains(pd) : rotatedShape.contains(pd);
    }

    @Override
    public void rotate(Graphics g, double angle) {
        AffineTransform a = new AffineTransform();
        a.rotate(angle, center.getX(), center.getY());
        this.rotatedShape = (Path2D.Double) a.createTransformedShape(this);
        System.out.println(this.rotatedShape.getClass());
        Graphics2D graphicsSettings = (Graphics2D) g;
        graphicsSettings.setStroke(new BasicStroke(2));
        graphicsSettings.setPaint(outlineColor);
        graphicsSettings.draw(this.rotatedShape);
        graphicsSettings.setPaint(fillColor);
        graphicsSettings.fill(this.rotatedShape);
    }

    @Override
    public void resize(Point p) {
        int vertex = getNearestVertex(p);
        xpoints[vertex] = p.x;
        ypoints[vertex] = p.y;
        this.center = new Point((int) this.getBounds().getCenterX(), (int) this.getBounds().getCenterY());
        for (int i = 0; i < 3; i++) {
            this.points[i] = new Point.Double(xpoints[i], ypoints[i]);
        }
        corners = new CornerRectangles(this);
    }

    private int getNearestVertex(Point p) {
        double[] distances = new double[3];
        for (int i = 0; i < 3; i++) {
            distances[i] = new Point(xpoints[i], ypoints[i]).distance(p);
        }
        int minIndex = -1;
        double minDistance = Long.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if (minDistance > distances[i]) {
                minDistance = distances[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    @Override
    public double getX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getWidth() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getHeight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawVertices(Graphics g) {
        if (rotatedShape == null) {
            Graphics2D graphics = (Graphics2D) g;
            graphics.setStroke(new BasicStroke(3));
            graphics.setPaint(Color.BLACK);
            for (Shape s : corners.getCornerRectangles()) {
                graphics.fill(s);
                graphics.draw(s);
            }
        }
    }

    @Override
    public void createVertices() {
        corners = new CornerRectangles(this);
    }
}
