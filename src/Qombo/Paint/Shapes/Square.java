/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Qombo.Paint.Shapes;

import java.awt.Point;

/**
 *
 * @author lo2ay
 */
public class Square extends Rectangle {

    public Square(int x, int y, int s) {
        super(x, y, s, s);
    }

    @Override
    public void resize(Point p) {

        switch (super.getNearestVertex(p)) {
            case 0: {
                int delta = (int) Math.min((p.x - this.x), (p.y - this.y));
                this.width -= delta;
                this.height -= delta;
                this.x += delta;
                this.y += delta;
                break;
            }
            case 1: {
                int dx = (int) (p.x - (this.x+this.width));
                int dy = (int) -(p.y - this.y);
                int delta;
                delta = (int) Math.max(dx, dy);
                System.out.println(delta);
                this.width+=delta;
                this.height+=delta;
                this.y-=delta;
                break;
            }
            case 2: {
                int delta = (int) Math.max((p.x - (this.x + this.width) ), (p.y - (this.y + this.height)));
                this.width += delta;
                this.height += delta;
                break;
            }
            case 3: {
                int dx = (int) -(p.x - this.x);
                int dy = (int) (p.y - (this.y + this.height));
                int delta;
                delta = (int) Math.max(dx, dy);
                this.width+=delta;
                this.height+=delta;
                this.x-=delta;
                break;
            }
        }
        
        this.createVertices();

    }
}
