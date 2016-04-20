/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.Shapes;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author lo2ay
 */
public interface Shape extends java.awt.Shape {
    @Override
    public Rectangle getBounds();
    @Override
    public Rectangle2D getBounds2D();
    @Override
    public boolean contains(double x, double y);
    @Override
    public boolean contains(Point2D p);
    @Override
    public boolean intersects(double x, double y, double w, double h);
    @Override
    public boolean intersects(Rectangle2D r);
    @Override
    public boolean contains(double x, double y, double w, double h);
    @Override
    public boolean contains(Rectangle2D r);
    @Override
    public PathIterator getPathIterator(AffineTransform at);
    @Override
    public PathIterator getPathIterator(AffineTransform at, double flatness);
}
