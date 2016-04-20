/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.Shapes;

import Qombo.Paint.Shapes.Shape;

/**
 *
 * @author lo2ay
 */
public class Line extends java.awt.geom.Line2D.Float implements Shape {
   
    public Line(int x1,int y1,int x2,int y2){
        super(x1, y1, x2, y2);
    }
}
