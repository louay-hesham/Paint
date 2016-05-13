/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.GUI;

import Qombo.Paint.Shapes.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Korra
 */
public class CornerRectangles {

    ArrayList<Rectangle> cornerRectangles = new ArrayList();
    private int i = 0;

    public CornerRectangles() {
        createCornerRectangles();

    }

    private void createCornerRectangles() {
        for (i = 0; i <= 3; i++) {
            cornerRectangles.add(new Rectangle(1, 1, 1, 1));
        }

    }
}
