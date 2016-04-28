/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.Shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author lo2ay
 */
public interface Shape extends java.awt.Shape {
    
    public void draw (Graphics g);
    
    public void setColor (Color newColor);
    
    public Shape clone();
}
