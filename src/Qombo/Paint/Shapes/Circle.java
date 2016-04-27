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
class Circle extends Ellipse {
    
    public final int ORDER;
    public Circle (int x, int y, int radius){
        super(x, y, radius, radius);
        ORDER = DrawingBoard.shapes.size()+1;
    }
}
