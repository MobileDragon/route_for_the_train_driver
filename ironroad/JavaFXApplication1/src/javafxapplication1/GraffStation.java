/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import static javafxapplication1.GraffPanel.gc;

/**
 *
 * @author bio
 */
public class GraffStation extends GraffE {
    
    GraffStation(String name){
        super(name);
    }
    GraffStation(int x, int y,String name){
        super(x,y,name);
    }
    
    
    public void draw(GraphicsContext gc){
        int centerD=(int) Math.pow( (Math.pow(size,2)+(Math.pow(size,2))), 0.5)/2;
        xPos-=centerD;
        yPos-=centerD;
        
        gc.setFill(Color.CORNSILK);
        gc.setStroke(Color.DARKORANGE);
        gc.setLineWidth(4);
        gc.strokeRect(xPos, yPos, size, size-size/3);
        gc.fillRect(xPos, yPos-4, size, size-size/3+4);
        
        double[] xPoints={xPos+size/2,xPos+size+size/4+2,xPos-size/4-2};
        double[] yPoints={yPos-size/3-4,yPos-2,yPos-2};
        if(activated)
            gc.setFill(Color.BLUE);
        else
            gc.setFill(Color.CORAL);
        gc.fillPolygon(xPoints, yPoints, 3);
        gc.setFill(Color.DARKBLUE);
        gc.setStroke(Color.CORNSILK);
        gc.setLineWidth(3);
        gc.strokeText(name, xPos, yPos+size);
        gc.fillText(name, xPos, yPos+size);
       
        xPos+=centerD;
        yPos+=centerD;
    }
}
