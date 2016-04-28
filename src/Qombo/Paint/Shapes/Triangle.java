/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.Shapes;

import Qombo.Paint.GUI.DrawingBoard;
import Qombo.Paint.GUI.MainGUI;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

/**
 *
 * @author lo2ay
 */
public class Triangle extends Polygon{
    private final int ORDER;
    private Point[] vertices;
    private Color fillColor,outlineColor;
    
    public Point[] getVertices() {
        return vertices;
    }
    public Triangle (int[] xpoints, int[] ypoints){
        super(xpoints, ypoints, 3);
        this.fillColor = MainGUI.getFillColor();
        this.outlineColor = MainGUI.getOutlineColor();
                ORDER = DrawingBoard.shapes.size()+1;
    }
    
    public void draw (Graphics2D graphicsSettings){
        graphicsSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            graphicsSettings.setPaint(outlineColor);
            graphicsSettings.draw(this);
            graphicsSettings.setPaint(fillColor);
            graphicsSettings.fill(this);
    }
}
