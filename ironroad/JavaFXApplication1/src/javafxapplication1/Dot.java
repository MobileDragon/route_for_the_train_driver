/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication1;

import java.util.ArrayList;

/**
 *
 * @author bio
 */
public class Dot {
    public boolean visit;
    public int price;
    public ArrayList<String>way;
    //ArrayList<String> states;
    public Dot(){}
    public Dot(Dot dot){
        visit=dot.visit;
        price=dot.price;
        way=new ArrayList<String>(dot.way);
    }
}
