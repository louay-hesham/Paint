/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.Shapes;

import Qombo.Logging.Logging;
import java.util.ArrayList;

/**
 *
 * @author lo2ay
 */
public interface Shape extends java.awt.Shape,Logging {
    static ArrayList<Shape> shapes = new ArrayList();
}
