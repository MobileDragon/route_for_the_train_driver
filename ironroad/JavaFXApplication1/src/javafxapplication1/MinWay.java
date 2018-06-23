/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import static javafxapplication1.GraffPanel.gc;

/**
 *
 * @author bio
 */
public class MinWay {
    
    
    //private Dot[] dot;
    Map dot=new HashMap<String, Dot>();
    
    private ArrayList<Line> lines;
    
    public MinWay() throws SQLException
    {
        
        //dot = new Dot[size];
        lines = new ArrayList<Line>();
        Dot t = new Dot();
        t.visit = false;
        t.price = 100000;
        t.way = new ArrayList<String>();
        
        ResultSet rs=BofD.make("SELECT DISTINCT start_id FROM rail_lines");
        while(rs.next()){
            String name=rs.getString("start_id");
            dot.put(name, t);
        }
        AddLine();
    }
    
    public void AddLine() throws SQLException
    {
        ResultSet rs=BofD.make("SELECT start_id,end_id,length FROM rail_lines");
        while(rs.next()){
            Line t=new Line();
            t.dot1=rs.getString("start_id");
            t.dot2=rs.getString("end_id");
            t.Value=rs.getInt("length");
            //
            lines.add(t);
        }
    }
    
    public ArrayList<String> WhoCanSee(String d1)//do
    {
        //ResultSet rs=BofD.make("SELECT DISTINCT start_id FROM rail_lines");
        
        ArrayList<String> masd2 = new ArrayList<String>();
        if (lines == null)
            return masd2;

        for (Line line : lines)
        {
            if (line.dot1.compareTo(d1)==0)
                masd2.add(line.dot2);
            /*
            else
                if (line.dot2.compareTo(d1)==0)//убрать для направления
                    masd2.add(line.dot1);
                    */
        }
        return masd2;
    }
    public int PriceLine(String peac1, String peac2)
    {
        if (lines == null)
            return(-1);
        Line t = new Line();
        t.Value=-1;
        for (Line line : lines)
        {
            if (line.dot1.compareTo(peac1)==0 && line.dot2.compareTo(peac2)==0)
                t = line;
            if (line.dot1.compareTo(peac2)==0 && line.dot2.compareTo(peac1)==0)//направления
                //t = line;
                return(10000);
        }
        return(t.Value);
    }
    
    public ArrayList<String> MinimumWay(String peak1, String peak2)
    {
        System.out.println("Ok1");
        dot.forEach( new BiConsumer() {//удалить
                public void accept(Object key, Object value) {
                    System.out.println("Ok1_1");
                    Dot temp = new Dot();
                    temp.price = 10000;
                    temp.visit = false;
                    temp.way= new ArrayList<String>();
                    dot.replace(key, temp);
                }
            });
        
        System.out.println("Ok2");
        Dot temp = new Dot();
        temp.way= new ArrayList<String>();
        temp.way.add(peak1);
        temp.price = 0;
        
        
        System.out.println("Ok2_1");
        dot.replace(peak1, temp);

        System.out.println("Ok3");
        ArrayList<String> watch = new ArrayList<String>();//исследуемые вершины

        watch.add(peak1);
        System.out.println("Ok4");
        String i=peak1;
        int index = 0;
        while(index<watch.size())
        {
            System.out.println("Ok5");
            i = watch.get(index++);
            if (i.compareTo(peak2)!=0 )
            {
                System.out.println("Ok6_0");
                Dot temp1 = new Dot((Dot)dot.get(i));
                temp1.visit=true;
                dot.replace(i, temp1);
                //dot[i].visit = true;
                //
                ArrayList<String> canSee=WhoCanSee(i);
                for (String j : canSee)
                {
                    System.out.println("Ok6");
                    if ( !( (Dot)dot.get(j) ).visit)//если вершина не есть полностью исследована
                    {

                        if (( (Dot)dot.get(j) ).price > ( (Dot)dot.get(i) ).price + PriceLine(i, j))//был ли найден более дешевый путь к вершине?
                        {
                            Dot temp2 = new Dot((Dot)dot.get(j));
                            temp2.way=new ArrayList<String>(( (Dot)dot.get(i) ).way);
                            temp2.way.add(j);
                            temp2.price=( (Dot)dot.get(i) ).price+PriceLine(i, j);
                            dot.replace(j, temp2);
                            System.out.println("Ok7");
                            //dot[j].way =new ArrayList<Integer>(dot[i].way);
                            //dot[j].way.add(j);
                            //dot[j].price = dot[i].price + PriceLine(i, j);
                            watch.add(j);
                        }
                    }
                }
            }  
        }
        System.out.println("Ok8 "+( (Dot)dot.get(peak2) ).way.size());
        return (( (Dot)dot.get(peak2) ).way);
    }
}
