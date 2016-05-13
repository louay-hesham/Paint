/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.Shapes;

import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author lo2ay
 */
public interface Shape extends java.awt.Shape, Cloneable {

    public void draw(Graphics g);

    public void setColor();

    public Shape clone();

    public void setPosition(Point p);

    public void rotate(Graphics g, double angle);

    public Point getCenter();

    public void resize(Point p);

    public double getX();

    public double getY();

    public double getWidth();

    public double getHeight();

    public void drawVertices(Graphics g);

    public void createVertices();
}
