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
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

/**
 *
 * @author lo2ay
 */
public class Ellipse extends java.awt.geom.Ellipse2D.Float implements Shape {

    private Color fillColor, outlineColor;
    private Point center;
    private Path2D.Double rotatedShape = null;
    public CornerRectangles corners;

    public Ellipse(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fillColor = MainGUI.getFillColor();
        this.outlineColor = MainGUI.getOutlineColor();
        this.center = new Point((int) this.getBounds().getCenterX(), (int) this.getBounds().getCenterY());
    }

    @Override
    public void draw(Graphics g) {
        java.awt.Shape shape = (this.rotatedShape == null ? this : rotatedShape);
        Graphics2D graphicsSettings = (Graphics2D) g;
        graphicsSettings.setStroke(new BasicStroke(3));
        graphicsSettings.setPaint(outlineColor);
        graphicsSettings.draw(shape);
        graphicsSettings.setPaint(fillColor);
        graphicsSettings.fill(shape);
    }

    @Override
    public void drawVertices(Graphics g) {
        if (rotatedShape == null) {
            Graphics2D graphics = (Graphics2D) g;
            graphics.setStroke(new BasicStroke(3));
            graphics.setPaint(Color.BLACK);
            for (Shape s : corners.getCornerRectangles()) {
                graphics.draw(s);
            }
            graphics.setPaint(Color.BLACK);
            for (Shape s : corners.getCornerRectangles()) {
                graphics.fill(s);
            }
        }
    }

    @Override
    public Point getCenter() {
        return this.center;
    }

    @Override
    public void setColor() {
        this.fillColor = MainGUI.getFillColor();
        this.outlineColor = MainGUI.getOutlineColor();
    }

    @Override
    public Shape clone() {
        Ellipse cloneEllipse = new Ellipse((int) x, (int) y, (int) width, (int) height);
        cloneEllipse.fillColor = this.fillColor;
        cloneEllipse.outlineColor = this.outlineColor;
        cloneEllipse.rotatedShape = this.rotatedShape == null ? null : (Path2D.Double) this.rotatedShape.clone();
        return cloneEllipse;
    }

    @Override
    public void setPosition(Point p) {
        if (this.rotatedShape != null) {
            AffineTransform transform = new AffineTransform();
            transform.translate(p.getX() - center.getX(), p.getY() - center.getY());
            this.rotatedShape.transform(transform);
        }
        this.x = (int) p.getX() - width / 2;
        this.y = (int) p.getY() - height / 2;
        this.center = new Point((int) this.getBounds().getCenterX(), (int) this.getBounds().getCenterY());
    }

    @Override
    public boolean contains(Point2D pd) {
        if (this.rotatedShape!=null){
            return rotatedShape.contains(pd);
        }
        if (MainGUI.getCurrentAction() == 12){
            return this.getBounds().contains(pd);
        }
        return super.contains(pd);
    }

    @Override
    public void rotate(Graphics g, double angle) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(angle, center.getX(), center.getY());
        this.rotatedShape = (Path2D.Double) transform.createTransformedShape(this);
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
        switch (getNearestVertex(p)) {
            case 0:
                this.width -= (p.x - this.x);
                this.height -= (p.y - this.y);
                this.x = p.x;
                this.y = p.y;
                break;
            case 1:
                this.width = p.x - this.x;
                this.height -= (p.y - this.y);
                this.y = p.y;
                break;
            case 2:
                this.width = p.x - this.x;
                this.height = p.y - this.y;
                break;
            case 3:
                this.width -= (p.x - this.x);
                this.x = p.x;
                this.height = p.y - this.y;
                break;
        }
        this.createVertices();
    }

    protected int getNearestVertex(Point p) {
        double[] distances = {p.distance(x, y),
            p.distance(x + width, y),
            p.distance(x + width, y + height),
            p.distance(x, y + height)};
        int minIndex = -1;
        double minDistance = Long.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            if (minDistance > distances[i]) {
                minDistance = distances[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    @Override
    public void createVertices() {
        corners = new CornerRectangles(CornerRectangles.RECTANGLE, this);
    }
}
