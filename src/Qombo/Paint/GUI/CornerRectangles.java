/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.GUI;

import Qombo.Paint.Shapes.Rectangle;
import Qombo.Paint.Shapes.Shape;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Korra
 */
public class CornerRectangles {

    public static int RECTANGLE = 1;
    public static int TRIANGLE = 2;

    public ArrayList<Rectangle> cornerRectangles = new ArrayList();

    public ArrayList<Rectangle> getCornerRectangles() {
        return cornerRectangles;
    }
    private int i = 0;

    public CornerRectangles(int shapeName, Shape shape) {
        if (shapeName == 1) {
           
            createSquareCornerRectangles();
            int x = (int) shape.getX();
            int y = (int) shape.getY();
            int width = (int) shape.getWidth();
            int height = (int) shape.getHeight();
            cornerRectangles.get(0).setPosition(new Point (x,y));
            cornerRectangles.get(1).setPosition(new Point(x, y + height));
            cornerRectangles.get(2).setPosition(new Point(x + width, y));
            cornerRectangles.get(3).setPosition(new Point(x + width, y + height));   
        }
        if (shapeName == 2) {
            createTriangleCornerRectangles();
        }
        

    }

    private void createSquareCornerRectangles() {
        for (i = 0; i <= 3; i++) {
            cornerRectangles.add(new Rectangle(5, 5, 5, 5));
        }

    }

    private void createTriangleCornerRectangles() {
        for (i = 0; i <= 2; i++){
            cornerRectangles.add(new Rectangle(1,1,1,1));
        }

    }
}
