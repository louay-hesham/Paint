/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.Shapes;

import Qombo.Paint.GUI.MainGUI;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 *
 * @author lo2ay
 */
public class Rectangle extends java.awt.geom.Rectangle2D.Float implements Shape {

    private Color fillColor, outlineColor;
    private Point center;

    public Rectangle(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.fillColor = MainGUI.getFillColor();
        this.outlineColor = MainGUI.getOutlineColor();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.center = new Point((int) this.getBounds().getCenterX(), (int) this.getBounds().getCenterY());
    }

    @Override
    public Point getCenter() {
        return this.center;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D graphicsSettings = (Graphics2D) g;
        graphicsSettings.setStroke(new BasicStroke(2));
        graphicsSettings.setPaint(outlineColor);
        graphicsSettings.draw(this);
        graphicsSettings.setPaint(fillColor);
        graphicsSettings.fill(this);
    }

    @Override
    public void setColor() {
        this.fillColor = MainGUI.getFillColor();
        this.outlineColor = MainGUI.getOutlineColor();
    }

    @Override
    public Shape clone() {
        Rectangle cloneRec = new Rectangle((int) x, (int) y, (int) width, (int) height);
        cloneRec.fillColor = this.fillColor;
        cloneRec.outlineColor = this.outlineColor;
        return cloneRec;
    }

    @Override
    public void setPosition(Point p) {
        this.x = (int) p.getX() - width / 2;
        this.y = (int) p.getY() - height / 2;
        this.center = new Point((int) this.getBounds().getCenterX(), (int) this.getBounds().getCenterY());
    }

    @Override
    public void rotate(Graphics g, double angle) {
        AffineTransform a = new AffineTransform();
        a.rotate(angle, center.getX(), center.getY());
        java.awt.Shape tempShape = a.createTransformedShape(this);
        System.out.println(tempShape.getClass());
        Graphics2D graphicsSettings = (Graphics2D) g;
        graphicsSettings.draw(tempShape);
    }
}
