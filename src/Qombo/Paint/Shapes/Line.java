/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.Shapes;

import Qombo.Paint.GUI.DrawingBoard;
import java.awt.Point;

/**
 *
 * @author lo2ay
 */
class Line extends java.awt.geom.Line2D.Float {
       
    public final int ORDER;
    public Line(Point p1, Point p2){
        super(p1, p2);
        ORDER = DrawingBoard.shapes.size()+1;
    }
}
