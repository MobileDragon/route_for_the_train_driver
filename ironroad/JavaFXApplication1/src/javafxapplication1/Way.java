/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication1;

import java.util.Calendar;

/**
 *
 * @author bio
 */
public class Way {//тип данных для таблицы
    private String station;
    private Calendar timeArrive;
    private String timeWait;
 
    public Way(String station, Calendar timeArrive, String timeWait) {
        this.station = station;
        this.timeArrive = timeArrive;
        this.timeWait = timeWait;
    }
 
    public Way() {
    }
 
    public void setStation(String station) {
        this.station = station;
    }
 
    public String getStation() {
        return station;
    }
 
    public void setTimeArrive(Calendar timeArrive) {
        this.timeArrive = timeArrive;
    }
 
    public Calendar getTimeArrive() {
        return timeArrive;
    }
 
    public void setTimeWait(String timeWait) {
        this.timeWait = timeWait;
    }
 
    public String getTimeWait() {
        return timeWait;
    }

}
