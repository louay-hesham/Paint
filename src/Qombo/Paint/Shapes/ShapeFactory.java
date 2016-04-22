/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.Shapes;

import java.awt.Point;

/**
 *
 * @author lo2ay
 */
public class ShapeFactory {
    
    public Rectangle drawRectangle(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);

        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);
        return new Rectangle(x, y, width, height);

    }

    public Square drawSquare(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);

        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);
        int side = Math.max(width, height);
        return new Square(x, y, side);

    }

    public Ellipse drawEllipse(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);

        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);
        return new Ellipse(x, y, width, height);
    }

    public Line drawLine(Point p1, Point p2) {
        return new Line(p1, p2);
    }

    public Circle drawCircle(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);

        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);
        int radius = Math.max(width, height);
        return new Circle(x, y, radius);
    }

    public Triangle drawTriangle(Point[] v) {
        int[] x = new int[]{(int) v[0].getX(), (int) v[1].getX(), (int) v[2].getX()};
        int[] y = new int[]{(int) v[0].getY(), (int) v[1].getY(), (int) v[2].getY()};
        return new Triangle(x, y);
    }

    public Ellipse drawBrush(int x, int y, int brushStrokeWidth, int brushStrokeHeight) {
        return new Ellipse(x, y, brushStrokeWidth, brushStrokeHeight);
    }
}
