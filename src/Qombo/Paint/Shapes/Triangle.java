/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.Shapes;

import java.awt.Point;
import java.awt.Polygon;

/**
 *
 * @author lo2ay
 */
class Triangle extends Polygon{
    private Point[] vertices;

    public Point[] getVertices() {
        return vertices;
    }
    public Triangle (int[] xpoints, int[] ypoints){
        super(xpoints, ypoints, 3);
        
    }
    
    
}
