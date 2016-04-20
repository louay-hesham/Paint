/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.Shapes;

import java.util.ArrayList;

/**
 *
 * @author lo2ay
 */
public interface Shape extends java.awt.Shape {
    public default void log(String str){
        System.out.println(str);
    }
    static ArrayList<Shape> shapes = new ArrayList();
    int ORDER=Shape.shapes.size();
}
