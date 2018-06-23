/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author bio
 */
public class GraffFork extends GraffE{
    
    GraffFork(String name){
        super(name);
    }
    GraffFork(int x, int y,String name){
        super(x,y,name);
    }
    
    
    public void draw(GraphicsContext gc){
        int centerD=(int) Math.pow( (Math.pow(size,2)+(Math.pow(size,2))), 0.5)/2;
        xPos-=centerD;
        yPos-=centerD;
        gc.setFill(Color.GREENYELLOW);
        gc.fillOval(xPos, yPos, size, size);
        if(activated)
            gc.setStroke(Color.BLUE);
        else
            gc.setStroke(Color.BROWN);
        gc.setLineWidth(1);
        gc.strokeOval(xPos, yPos, size, size);
        gc.setFill(Color.DARKBLUE);
        gc.setStroke(Color.CORNSILK);
        gc.setLineWidth(2);
        gc.strokeText(name, xPos, yPos);
        gc.fillText(name, xPos, yPos);
        
        xPos+=centerD;
        yPos+=centerD;
    }
}
