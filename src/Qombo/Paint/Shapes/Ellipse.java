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
        java.awt.Shape shape = (this.rotatedShape == null ? this : rotatedShape);
        Graphics2D graphicsSettings = (Graphics2D) g;
        graphicsSettings.setStroke(new BasicStroke(3));
        graphicsSettings.setPaint(outlineColor);
        graphicsSettings.draw(shape);
        graphicsSettings.setPaint(fillColor);
        graphicsSettings.fill(shape);
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
        cloneEllipse.rotatedShape = (Path2D.Double)this.rotatedShape.clone();
        return cloneEllipse;
    }

    @Override
    public void setPosition(Point p) {
        if (this.rotatedShape != null) {
            AffineTransform transform = new AffineTransform();
            transform.translate(p.getX() - center.getX(), p.getY() - center.getY());
            this.rotatedShape.transform(transform);
        }
        this.x = (int)p.getX() - width/2;
        this.y = (int)p.getY() - height/2;
        this.center = new Point((int) this.getBounds().getCenterX(), (int) this.getBounds().getCenterY());
    }
    
    @Override
    public boolean contains(Point2D pd) {
        return this.rotatedShape==null? super.contains(pd):rotatedShape.contains(pd);
    }
    
    @Override
    public void rotate(Graphics g, double angle) {
        AffineTransform transform = new AffineTransform();
        transform.rotate(angle, center.getX(), center.getY());
        this.rotatedShape = (Path2D.Double)transform.createTransformedShape(this);
        System.out.println(this.rotatedShape.getClass());
        Graphics2D graphicsSettings = (Graphics2D) g;
        graphicsSettings.setStroke(new BasicStroke(2));
        graphicsSettings.setPaint(outlineColor);
        graphicsSettings.draw(this.rotatedShape);
        graphicsSettings.setPaint(fillColor);
        graphicsSettings.fill(this.rotatedShape);
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
