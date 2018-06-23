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
public class GraffRoad extends GraffE{
    
    GraffRoad(String name){
        super(name);
    }
    public GraffRoad(int x1, int y1,int x2, int y2,String name){
        super(x1,y1,x2,y2,name);
    }

    
    @Override
    public void draw(GraphicsContext gc){
        
        double a=(yCon-yPos);
        double c=(xCon-xPos);
        double b=(int) Math.pow( (Math.pow(a,2)+(Math.pow(c,2))), 0.5);

        double angl=Math.atan2(a,c)*180/Math.PI;//

        System.out.println("запросj "+a/c+"    |"+angl);
        int xPost=(int)(Math.cos((angl)*Math.PI/180)*size/2+xPos);
        int yPost=(int)(Math.sin((angl)*Math.PI/180)*size/2+yPos);
        int xCont=(int)(Math.cos((angl)*Math.PI/180)*(b-1.2*size)+xPos);
        int yCont=(int)(Math.sin((angl)*Math.PI/180)*(b-1.2*size)+yPos);
        
        double angl1=angl+180+20;
        double angl2=angl+180-20;
        
        double[] xPoints=new double[3];
        double[] yPoints=new double[3];
        
        xPoints[0]=xCont;
        yPoints[0]=yCont;
        xPoints[1]=Math.cos((angl1)*Math.PI/180)*size+xCont;
        yPoints[1]=Math.sin((angl1)*Math.PI/180)*size+yCont;
        xPoints[2]=Math.cos((angl2)*Math.PI/180)*size+xCont;
        yPoints[2]=Math.sin((angl2)*Math.PI/180)*size+yCont;
        
        
        gc.setLineWidth(2);
        if(activated)
           gc.setStroke(Color.BLUE);
        else
        gc.setStroke(Color.BROWN);

        gc.strokeLine(xPoints[0], yPoints[0], xPoints[1], yPoints[1]);
        gc.strokeLine(xPoints[0], yPoints[0], xPoints[2], yPoints[2]);
        //gc.strokePolygon(xPoints, yPoints, 3);
        
        gc.strokeLine(xPost, yPost, xCon, yCon);
        gc.setStroke(Color.CORNSILK);
        int xMid=(xPost+xCont)/2-16;
        int yMid=(yPost+yCont)/2;
        gc.setLineWidth(2);
        gc.strokeText(name, xMid, yMid);
        gc.setFill(Color.DARKBLUE);
        gc.fillText(name, xMid, yMid);
       
    }
}
