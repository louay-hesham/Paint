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
import java.awt.Polygon;
import java.awt.RenderingHints;

/**
 *
 * @author lo2ay
 */
public class Triangle extends Polygon implements Shape{
    private Point[] vertices;
    private Color fillColor,outlineColor;
    int[] xpoints,ypoints;
    public Point[] getVertices() {
        return vertices;
    }
    public Triangle (int[] xpoints, int[] ypoints){
        super(xpoints, ypoints, 3);
        this.fillColor = MainGUI.getFillColor();
        this.outlineColor = MainGUI.getOutlineColor();
        this.xpoints=xpoints;
        this.ypoints=ypoints;

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
    public void setColor(Color newColor) {
        this.fillColor=newColor;
    }

    @Override
    public Shape clone() {
        Triangle cloneTriangle = new Triangle(xpoints, ypoints);
        cloneTriangle.fillColor=this.fillColor;
        cloneTriangle.outlineColor=this.outlineColor;
        return cloneTriangle;
    }
}
