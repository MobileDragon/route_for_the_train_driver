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
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.geometry.VPos;
import javafx.scene.canvas.*;
import javafx.scene.control.Button;
import javafx.scene.paint.*;



/**
 *
 * @author bio
 */


public class GraffPanel extends Canvas{
        public static GraphicsContext gc;
        protected Map elements=new HashMap<String, GraffE>();//станции и вилки
        protected Map linesR=new HashMap<String, GraffE>();// дороги
        Map elementsT=new HashMap<String, GraffE>();
        //protected Map<String, GraffE> linesR;// дороги
        MinWay deykstra;
        public GraffPanel(){
            try {
                fill();
            } catch (SQLException ex) {
                Logger.getLogger(GraffPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.setWidth(300);
            this.setHeight(340);
            gc = this.getGraphicsContext2D();
            
            try {
                deykstra=new MinWay();
            } catch (SQLException ex) {
                Logger.getLogger(GraffPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void paint(){
            
            //GraffRoad r=new GraffRoad(100,100,160,40,"test");
            //r.draw(gc);
            gc.clearRect(0, 0, this.getWidth(),this.getHeight() );
            
            linesR.forEach( new BiConsumer() {

                public void accept(Object key, Object value) {
                    ((GraffE)value).draw(gc);
                }
            });
            elements.forEach( new BiConsumer() {

                public void accept(Object key, Object value) {
                    ((GraffE)value).draw(gc);
                }
            });

        }
        
        
        private void fixP(String name, int posX, int posY)
        {
            GraffE a = (GraffE)elements.get(name);
            a.setPosition(posX, posY);
            elementsT.put(name, a);
        }
        protected void iDraw()//установка координат для станций и розвилок
        {
            fixP("vilka1",80,80);
            fixP("vilka2",270,40);
            fixP("vilka3",250,90);
            fixP("vilka7",250,200);
            fixP("vilka8",48,20);
            fixP("vilka9",260,331);
            fixP("vilka10",120,320);
            fixP("vilka11",185,280);
            fixP("vilka12",90,210);
            fixP("vilka13",10,210);
            fixP("vilka90",275,270);
            //
            fixP("voronej",15,26);
            fixP("Роздос",270,170);
            fixP("verdichev",70,300);
            fixP("tyhda",55,190);
            fixP("snegowsk",240,15);
            
            
            elements=elementsT;
        }
        
        public void activatedE(String name, boolean f)//подсветить станцию или вилку
        {
            GraffE temp= (GraffE)elements.get(name);
            temp.activated=f;
            elements.replace(name, temp);
            //------------------------------------------------------------------------
        }
        public void activatedR(String name, boolean f)//подсветить дорогу
        {
            GraffE temp= (GraffE)linesR.get(name);
            temp.activated=f;
            linesR.replace(name, temp);
            //------------------------------------------------------------------------
        }
        public String getVilkaOfSt(String statoin) throws SQLException
        {
            ResultSet rs=BofD.make("SELECT DISTINCT stations.name, rail_lines.end_id as vilka FROM stations_position, stations, rail_lines WHERE \n" +
                                    "(stations.id=stations_position.stations_id) and (rail_lines.id=stations_position.lines_id) \n" +
                                    "and stations_position.distance>1 and stations.name LIKE'"+statoin+"'");
            rs.next();
                String vilka=rs.getString("vilka");
            return vilka;
        }
        public void disaktivated()
        {
            elements.forEach( new BiConsumer() {

                public void accept(Object key, Object value) {
                    GraffE temp=((GraffE)value);
                    temp.activated=false;
                    elements.replace(key, temp);
                }
            });
            linesR.forEach( new BiConsumer() {

                public void accept(Object key, Object value) {
                    GraffE temp=((GraffE)value);
                    temp.activated=false;
                    linesR.replace(key, temp);
                }
            });
        }
        public int build(ObservableList<Way> stationDr) throws SQLException//построить маршрут,-отобразить
        {
            int length=0;
            disaktivated();
            String startSt=JavaFXApplication1.mySt, endSt="";
            startSt=getVilkaOfSt(startSt);
            //Way u=stationDr.get(3);
            for(int j=0;j<stationDr.size();j++){
                endSt=getVilkaOfSt(stationDr.get(j).getStation());
                
                //построить
                ArrayList<String> peakWay=deykstra.MinimumWay(startSt,endSt);
                for(int i=0;i<peakWay.size();i++){
                    String peak=peakWay.get(i);
                    activatedE(peak,true);
                    //
                    System.out.println("_28");
                    if(i+1<peakWay.size()){
                        ResultSet rs=BofD.make("SELECT id,length FROM rail_lines where start_id LIKE'"+peak
                                +"' and end_id LIKE'"+peakWay.get(i+1)+"'");
                        while(rs.next()){
                            System.out.println("_38");
                            String id=rs.getInt("id")+"";
                            length+=rs.getInt("length");
                            activatedR(id,true);
                        }
                    }
                }
                startSt=endSt;
            }
            paint();
            gc.setLineWidth(2);
            gc.strokeText("маршрут= "+length+" km", 2, 335);
            gc.setFill(Color.BLACK);
            gc.fillText("маршрут= "+length+" km", 2, 335);
            return length;
        }
        
        protected void fill() throws SQLException
        {

            ResultSet rs=BofD.make("SELECT DISTINCT start_id FROM rail_lines");
            while(rs.next()){
                String name=rs.getString("start_id");
                System.out.println(name);
                GraffFork h=new GraffFork(name);
                elements.put(name, h);
            }
            System.out.println("контакт38");
            rs=BofD.make("SELECT name FROM stations");
            while(rs.next()){
                String name=rs.getString("name");
                elements.put(name, new GraffStation(name));
            }
            //метод iDraw
            iDraw();

            rs=BofD.make("SELECT id,length,start_id,end_id FROM rail_lines");
            while(rs.next()){
                String name=rs.getInt("id")+"";
                String length=rs.getInt("length")+"";
                String start=rs.getString("start_id");
                String end=rs.getString("end_id");
                
                GraffRoad r=new GraffRoad(name+"/"+length+"km");
                r.setStart( ( (GraffE)(elements.get(start)) ).getStart() );//что за ужас(кошмар)
                r.setEnd( ( (GraffE)(elements.get(end)) ).getStart() );//из-за того что боится редактирования()
                linesR.put(name, r);
                
            }
            System.out.println("контакт58");
        }
       
    
}
