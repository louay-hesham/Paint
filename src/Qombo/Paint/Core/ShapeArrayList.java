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

        for (Iterator it = this.iterator(); it.hasNext();) {
            E object = (E) it.next();
            newArrayList.add(object.clone());
        }

        return newArrayList;
    }
}
