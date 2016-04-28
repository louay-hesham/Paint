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

/**
 *
 * @author lo2ay
 */
public class Line extends java.awt.geom.Line2D.Float {
       
    public final int ORDER;
     private Color lineColor;
     
    public Line(Point p1, Point p2){
        super(p1, p2);
        ORDER = DrawingBoard.shapes.size()+1;
                this.lineColor = MainGUI.getOutlineColor();
    }
    public void draw (Graphics2D graphicsSettings){
        graphicsSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            graphicsSettings.setPaint(lineColor);
            graphicsSettings.draw(this);
    }
}
