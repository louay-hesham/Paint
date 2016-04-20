/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.Shapes;

import Qombo.Paint.GUI.DrawingBoard;
import Qombo.Paint.Shapes.Shape;

/**
 *
 * @author lo2ay
 */
public class Ellipse extends java.awt.geom.Ellipse2D.Float implements Shape{
    
    public final int ORDER;
    public Ellipse(int x, int y, int width, int height){
        super(x, y, width, height);
        ORDER = DrawingBoard.shapes.size()+1;
    }
}
