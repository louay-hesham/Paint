/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.Shapes;

import Qombo.Paint.GUI.DrawingBoard;

/**
 *
 * @author lo2ay
 */
public class Line extends java.awt.geom.Line2D.Float {
       
    public final int ORDER;
    public Line(int x1,int y1,int x2,int y2){
        super(x1, y1, x2, y2);
        ORDER = DrawingBoard.shapes.size()+1;
    }
}
