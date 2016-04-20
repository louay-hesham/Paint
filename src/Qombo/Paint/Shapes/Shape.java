/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.Shapes;

import Qombo.Paint.GUI.DrawingBoard;
import java.util.ArrayList;

/**
 *
 * @author lo2ay
 */
public interface Shape extends java.awt.Shape {
    public default void log(String str){
        System.out.println(str);
    }
    public default void log(int n){
        System.out.println(n);
    }
    static ArrayList<Shape> shapes = new ArrayList();
    int ORDER=DrawingBoard.shapes.size()+1;
}
