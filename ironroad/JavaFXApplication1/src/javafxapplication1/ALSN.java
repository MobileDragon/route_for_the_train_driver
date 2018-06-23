/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication1;

/**
 *
 * @author bio
 */
public class ALSN {
    private int N;
    private int distance;
    private String alsn;
    private int piket;
    private int num_section;
    private String stoplight;
    /*
    public ALSN(int distance, String alsn,int piket,int num_section,String stoplight) {
        this.distance = distance;
        this.alsn = alsn;
        this.piket = piket;
        this.num_section = num_section;
        this.stoplight = stoplight;
    }*/
    public ALSN(int N,int distance, String alsn,int piket,int num_section,String stoplight) {
        this.N=N;
        this.distance = distance;
        this.alsn = alsn;
        this.piket = piket;
        this.num_section = num_section;
        this.stoplight = stoplight;
    }
    public ALSN() {
    }
    public void setN(int N) {
        this.N = N;
    }
    public int getN() {
        return N;
    }
    public void setDistance(int distance) {
        this.distance = distance;
    }
    public void setAlsn(String alsn) {
        this.alsn = alsn;
    }
    public void setPiket(int piket) {
        this.piket = piket;
    }
    public void setNum_section(int num_section) {
        this.num_section = num_section;
    }
    public void setStoplight(String stoplight) {
        this.stoplight = stoplight;
    }
    //
    public int getDistance() {
        return distance;
    }
    public String getAlsn() {
        return alsn;
    }
    public int getPiket() {
        return piket;
    }
    public int getNum_section() {
        return num_section;
    }
    public String getStoplight() {
        return stoplight;
    }
}

