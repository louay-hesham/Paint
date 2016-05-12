/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.Shapes;

import Qombo.Paint.GUI.MainGUI;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;

/**
 *
 * @author lo2ay
 */
public class Line extends java.awt.geom.Line2D.Float implements Shape {

    private Color lineColor;
    private Point p1, p2;
    private Point center;
    private double length;

    public Line(Point p1, Point p2) {
        super(p1, p2);
        this.p1 = p1;
        this.p2 = p2;
        this.lineColor = MainGUI.getOutlineColor();
        this.center = new Point((int) (p1.getX() + p2.getX()) / 2, (int) (p1.getY() + p2.getY()) / 2);
        this.length = Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }

    @Override
    public Point getCenter() {
        return this.center;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D graphicsSettings = (Graphics2D) g;
        graphicsSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphicsSettings.setStroke(new BasicStroke(2));
        graphicsSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        graphicsSettings.setPaint(lineColor);
        graphicsSettings.draw(this);
    }

    @Override
    public void setColor() {
        this.lineColor = MainGUI.getOutlineColor();
    }

    @Override
    public Shape clone() {
        Line cloneLine = new Line(p1, p2);
        cloneLine.lineColor = this.lineColor;
        return cloneLine;
    }

    @Override
    public void setPosition(Point p) {
        Point newP1 = new Point((int) (p.getX() - (this.p2.getX() - this.p1.getX()) / 2),
                (int) (p.getY() - (this.p2.getY() - this.p1.getY()) / 2));

        Point newP2 = new Point((int) (p.getX() + (this.p2.getX() - this.p1.getX()) / 2),
                (int) (p.getY() + (this.p2.getY() - this.p1.getY()) / 2));
        this.p1 = newP1;
        this.p2 = newP2;
        this.setLine(newP1, newP2);
        this.center = new Point((int) (p1.getX() + p2.getX()) / 2, (int) (p1.getY() + p2.getY()) / 2);
    }

    @Override
    public boolean contains(Point2D pd) {
        double lineSlope = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
        double pointSlope = (pd.getY() - p1.getY()) / (pd.getX() - p1.getX());
        if (Math.abs(lineSlope - pointSlope) <= 0.1
                && Math.min(p1.getX(), p2.getX()) <= pd.getX()
                && Math.max(p1.getX(), p2.getX()) >= pd.getX()
                && Math.min(p1.getY(), p2.getY()) <= pd.getY()
                && Math.max(p1.getY(), p2.getY()) >= pd.getY()) {
            return true;
        }
        return false;
    }

    @Override
    public void rotate(Graphics g, double angle) {
        
        angle/=100;
        double xOrigin = p1.x - center.x;
        double yOrigin = p1.y - center.y;

        double distX = xOrigin * Math.cos(angle) - yOrigin * Math.sin(angle);
        double distY = xOrigin * Math.sin(angle) + yOrigin * Math.cos(angle);

        p1.x = (int) (distX + center.x);
        p1.y = (int) (distY + center.y);
        p2.x = (int) (-distX + center.x);
        p2.y = (int) (-distY + center.y);
        
        this.setLine(this.p1, this.p2);
        this.draw(g);
    }

    

    @Override
    public void upSize(double xRatio, double yRatio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void downSize(double xRatio, double yRatio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
