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
import java.awt.RenderingHints;

/**
 *
 * @author lo2ay
 */
public class Ellipse extends java.awt.geom.Ellipse2D.Float implements Shape {

    public final int ORDER;
    private Color fillColor, outlineColor;

    public Ellipse(int x, int y, int width, int height) {
        super(x, y, width, height);
        ORDER = DrawingBoard.shapes.size() + 1;
        this.fillColor = MainGUI.getFillColor();
        this.outlineColor = MainGUI.getOutlineColor();
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
        this.fillColor = newColor;
    }
}
