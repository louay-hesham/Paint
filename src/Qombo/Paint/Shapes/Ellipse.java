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

/**
 *
 * @author lo2ay
 */
public class Ellipse extends java.awt.geom.Ellipse2D.Float{
    
    public final int ORDER;
        private Color fillColor,outlineColor;

    public Ellipse(int x, int y, int width, int height){
        super(x, y, width, height);
        ORDER = DrawingBoard.shapes.size()+1;
        this.fillColor = MainGUI.getFillColor();
        this.outlineColor = MainGUI.getOutlineColor();
    }
    
    public void draw (Graphics2D graphicsSettings){
        graphicsSettings.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            graphicsSettings.setPaint(outlineColor);
            graphicsSettings.draw(this);
            graphicsSettings.setPaint(fillColor);
            graphicsSettings.fill(this);
    }
}
