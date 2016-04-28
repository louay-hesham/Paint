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

/**
 *
 * @author lo2ay
 */
public class Line extends java.awt.geom.Line2D.Float implements Shape {

    public final int ORDER;
    private Color lineColor;

    public Line(Point p1, Point p2) {
        super(p1, p2);
        ORDER = DrawingBoard.shapes.size() + 1;
        this.lineColor = MainGUI.getOutlineColor();
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
    public void setColor(Color newColor) {
        this.lineColor = newColor;
    }
}
