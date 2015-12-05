/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Stefan
 */
public class Ball {

    
    Color red= new Color(255,0,0);
    
    int WIDTH,HEIGHT;
    boolean fill = false;
    
    double x,y;
    
  
    
    
    
    public Ball(int d1, int d2 , boolean f) {
        
          WIDTH = HEIGHT = 25;
          
          x = (d1/2) - (25/2)-400;
          y =  (d2/2) - (25/2)-200;
          //System.out.println("X is "+ x +" y is "+ y);
          fill = f;
          
       
        
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    
    public void setWIDTHHEIGHT(int x){
    
        WIDTH = HEIGHT = x;
        
    }

    public int getWIDTH() {
        return WIDTH;
    }

    
    
    
    
}
