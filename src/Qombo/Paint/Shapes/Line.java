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
import java.awt.geom.Point2D;

/**
 *
 * @author lo2ay
 */
public class Line extends java.awt.geom.Line2D.Float implements Shape {

    private Color lineColor;
    private Point.Double p1, p2;
    private Point.Double center;
    private double length;

    public Line(Point.Double p1, Point.Double p2) {
        super(p1, p2);
        this.p1 = p1;
        this.p2 = p2;
        this.lineColor = MainGUI.getOutlineColor();
        this.center = new Point.Double((p1.getX() + p2.getX()) / 2, (p1.getY() + p2.getY()) / 2);
        this.length = p1.distance(p2);
    }

    @Override
    public Point getCenter() {
        return new Point((int)center.x,(int)center.y);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D graphicsSettings = (Graphics2D) g;
        graphicsSettings.setStroke(new BasicStroke(3));
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
        Point.Double newP1 = new Point.Double((p.getX() - (this.p2.getX() - this.p1.getX()) / 2),
                (int) (p.getY() - (this.p2.getY() - this.p1.getY()) / 2));

        Point.Double newP2 = new Point.Double((int) (p.getX() + (this.p2.getX() - this.p1.getX()) / 2),
                (int) (p.getY() + (this.p2.getY() - this.p1.getY()) / 2));
        this.p1 = newP1;
        this.p2 = newP2;
        this.setLine(newP1, newP2);
        this.center = new Point.Double((int) (p1.getX() + p2.getX()) / 2, (int) (p1.getY() + p2.getY()) / 2);
    }

    @Override
    public boolean contains(Point2D pd) {
        double lineSlope = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
        double pointSlope = (pd.getY() - p1.getY()) / (pd.getX() - p1.getX());
        return Math.abs(lineSlope - pointSlope) <= 0.1
                && Math.min(p1.getX(), p2.getX()) <= pd.getX()
                && Math.max(p1.getX(), p2.getX()) >= pd.getX()
                && Math.min(p1.getY(), p2.getY()) <= pd.getY()
                && Math.max(p1.getY(), p2.getY()) >= pd.getY();
    }

    @Override
    public void rotate(Graphics g, double angle) {
        angle/=100;
        this.p1 = new Point.Double (p1.x-center.x,p1.y-center.y);
        double x = p1.x*Math.cos(angle) - p1.y*Math.sin(angle);
        double y = p1.x*Math.sin(angle) + p1.y*Math.cos(angle);
        p1 = new Point.Double(x,y);
        p2 = new Point.Double(-x,-y);
        p1.x+=center.x;
        p1.y+=center.y;
        p2.x+=center.x;
        p2.y+=center.y;
        this.setLine(this.p1, this.p2);
        this.draw(g);
    }

    @Override
    public void resize(Point p) {
        int vertex = getNearestVertex(p);
        switch (vertex){
            case 1:
                p1.setLocation(p);
                break;
            case 2: 
                p2.setLocation(p);
                break;
        }
        this.setLine(p1, p2);
        this.length = p1.distance(p2);
        this.center = new Point.Double((int) (p1.getX() + p2.getX()) / 2, (int) (p1.getY() + p2.getY()) / 2);
    }

    private int getNearestVertex(Point p) {
        return p1.distance(p)<p2.distance(p)? 1:2;
    }
}
