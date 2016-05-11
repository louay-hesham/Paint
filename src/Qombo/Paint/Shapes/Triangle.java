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
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

/**
 *
 * @author lo2ay
 */
public class Triangle extends Polygon implements Shape{

    private Color fillColor,outlineColor;
    private Point center;
    
    public Triangle (int[] xpoints, int[] ypoints){
        super(xpoints, ypoints, 3);
        this.fillColor = MainGUI.getFillColor();
        this.outlineColor = MainGUI.getOutlineColor();
        this.center = new Point((int) this.getBounds().getCenterX(), (int) this.getBounds().getCenterY());
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
        Triangle cloneTriangle = new Triangle(xpoints, ypoints);
        cloneTriangle.fillColor=this.fillColor;
        cloneTriangle.outlineColor=this.outlineColor;
        return cloneTriangle;
    }

    @Override
    public void setPosition(Point p) {
        int xCor= (int) this.getBounds2D().getCenterX();
        int yCor= (int) this.getBounds2D().getCenterY();
        this.translate((int)p.getX()-xCor, (int)p.getY()-yCor);
        this.center = new Point((int) this.getBounds().getCenterX(), (int) this.getBounds().getCenterY());
    }
    
    @Override
    public void rotate(Graphics g, double angle) {
        AffineTransform a = new AffineTransform();
        a.rotate(angle, center.getX(), center.getY());
        java.awt.Shape tempShape = a.createTransformedShape(this);
        System.out.println(tempShape.getClass());
        Graphics2D graphicsSettings = (Graphics2D) g;
        graphicsSettings.setStroke(new BasicStroke(2));
        graphicsSettings.setPaint(outlineColor);
        graphicsSettings.draw(tempShape);
        graphicsSettings.setPaint(fillColor);
        graphicsSettings.fill(tempShape);
    }
}
