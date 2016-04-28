/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.Core;

import Qombo.Paint.Shapes.Shape;
import java.util.Iterator;

/**
 *
 * @author Muhammad Korra
 */
public class ShapeArrayList<E extends Shape> extends java.util.ArrayList {

    @Override
    public ShapeArrayList clone(){
        ShapeArrayList newArrayList = new ShapeArrayList();

        for (int i = 0; i<this.size(); i++) {
            Shape shape = (Shape)super.get(i);
            if (shape==null){
                System.out.println("null 7amada");
            }
            newArrayList.add(shape.clone());
        }
        return newArrayList;
    }
}
