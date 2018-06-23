/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication1;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author bio
 */
public abstract class GraffE {
    protected int xPos=20;
    protected int yPos=20;
    protected int xCon=20;
    protected int yCon=20;
    protected int size=15;
    protected String name=null;
    public Boolean activated=false;
    public GraffE(int x,int y,String name)
    {
        setPosition(x, y);
        setName(name);
    }
    public GraffE(int x1, int y1,int x2, int y2,String name){
        xPos=x1;
        yPos=y1;
        xCon=x2;
        yCon=y2;
        setName(name);
    }
    //
    public GraffE(String name)
    {
        setName(name);
    }
    //
    public void setPosition(int x,int y)
    {
        xPos = x;
        yPos = y;
    }
    public void setPosition(int []arr)
    {
        xPos = arr[0];
        yPos = arr[1];
    }
    public void setSize(int size)
    {
        this.size=size;
    }
    public void setName(String name)//важно
    {
        this.name=name;
    }
    public String getName()
    {
        return name;
    }
    
    public int[] getStart()
    {
        int []arr={xPos,yPos};
        return arr;
    }

    public int[] getEnd()
    {
        int []arr={xCon,yCon};
        return arr;
    }
    public void setStart(int []arr)
    {
        xPos = arr[0];
        yPos = arr[1];
    }

    public void setEnd(int []arr)
    {
        xCon = arr[0];
        yCon = arr[1];
    }
    
    abstract public void draw(GraphicsContext gc);
}
