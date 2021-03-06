/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.Shapes;

import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 *
 * @author lo2ay
 */
public class ShapeFactory {
    
    public enum ShapeType {RECTANGLE, SQUARE, ELLIPSE, LINE, CIRCLE};
    
    private static ShapeFactory shapeFactory;
    
    public static ShapeFactory getFactory(){
        if (shapeFactory==null)
            shapeFactory = new ShapeFactory();
        return shapeFactory;
    }
        
    private ShapeFactory(){
        
    }
    
    public Shape getShape(Point p1, Point p2, ShapeType type){
        switch (type){
            case RECTANGLE:
                return drawRectangle(p1,p2);
            case SQUARE:
                return drawSquare(p1,p2);
            case ELLIPSE:
                return drawEllipse(p1,p2);
            case CIRCLE:
                return drawCircle(p1,p2);
            case LINE:
                return drawLine(p1,p2);
        }
        return null;
    }
    public Shape getShape(Point[] v){
        return drawTriangle(v);
    }
    
    /*public Shape rotateShape(Shape shape, double angle){
        AffineTransform a = new AffineTransform();
        a.rotate(angle, shape.getCenter().getX(), shape.getCenter().getY());
        java.awt.Shape tempShape = a.createTransformedShape(shape);
        Shape newShape=null;
        
        if (shape instanceof Rectangle){
            newShape = new Rectangle();
            ((Rectangle)newShape).setRect(tempShape);
        }
        return newShape;
    }*/
    
    private Rectangle drawRectangle(Point p1, Point p2) {
        int x = Math.min(p1.x, p2.x);
        int y = Math.min(p1.y, p2.y);

        int width = Math.abs(p1.x - p2.x);
        int height = Math.abs(p1.y - p2.y);
        return new Rectangle(x, y, width, height);

    }

    private Square drawSquare(Point p1, Point p2) {
        int x = Math.min(p1.x, p2.x);
        int y = Math.min(p1.y, p2.y);

        int width = Math.abs(p1.x - p2.x);
        int height = Math.abs(p1.y - p2.y);
        int side = Math.max(width, height);
        return new Square(x, y, side);

    }

    private Ellipse drawEllipse(Point p1, Point p2) {
        int x = Math.min(p1.x, p2.x);
        int y = Math.min(p1.y, p2.y);

        int width = Math.abs(p1.x - p2.x);
        int height = Math.abs(p1.y - p2.y);
        return new Ellipse(x, y, width, height);
    }

    private Line drawLine(Point p1, Point p2) {
        return new Line(new Point.Double(p1.x, p1.y),new Point.Double(p2.x, p2.y));
    }

    private Circle drawCircle(Point p1, Point p2) {
        int x = Math.min(p1.x, p2.x);
        int y = Math.min(p1.y, p2.y);

        int width = Math.abs(p1.x - p2.x);
        int height = Math.abs(p1.y - p2.y);
        int radius = Math.max(width, height);
        return new Circle(x, y, radius);
    }

    private Triangle drawTriangle(Point[] v) {
        int[] x = new int[]{(int) v[0].getX(), (int) v[1].getX(), (int) v[2].getX()};
        int[] y = new int[]{(int) v[0].getY(), (int) v[1].getY(), (int) v[2].getY()};
        return new Triangle(x, y);
    }
}
