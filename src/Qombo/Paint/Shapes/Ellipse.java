/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.Shapes;

import Qombo.Paint.GUI.DrawingBoard;
import Qombo.Paint.GUI.MainGUI;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

/**
 *
 * @author lo2ay
 */
public class Ellipse extends java.awt.geom.Ellipse2D.Float implements Shape {

    private Color fillColor, outlineColor;
    private Point center;

    public Ellipse(int x, int y, int width, int height) {
        super(x, y, width, height);             
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.fillColor = MainGUI.getFillColor();
        this.outlineColor = MainGUI.getOutlineColor();
        this.center = new Point((int) this.getBounds().getCenterX(), (int) this.getBounds().getCenterY());
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D graphicsSettings = (Graphics2D) g;
        graphicsSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphicsSettings.setStroke(new BasicStroke(2));
        graphicsSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        graphicsSettings.setPaint(outlineColor);
        graphicsSettings.draw(this);
        graphicsSettings.setPaint(fillColor);
        graphicsSettings.fill(this);
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
        Ellipse cloneEllipse= new Ellipse((int)x, (int)y, (int)width, (int)height);
        cloneEllipse.fillColor=this.fillColor;
        cloneEllipse.outlineColor=this.outlineColor;
        return cloneEllipse;
    }

    @Override
    public void setPosition(Point p) {
        this.x = (int)p.getX() - width/2;
        this.y = (int)p.getY() - height/2;
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
