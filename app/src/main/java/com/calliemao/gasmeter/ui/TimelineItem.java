package com.calliemao.gasmeter.ui;

/**
 * Created by alex on 11/7/15.
 */
public class TimelineItem {

    private String dayOfWeek;
    private String date;
    private int distance;
    private String units;

    TimelineItem(String dayOfWeek, String date, int distance, String units){
        this.dayOfWeek = dayOfWeek;
        this.date = date;
        this.distance = distance;
        this.units = units;
    }

    public String getDate() {
        return date;
    }

    public String getDayOfWeek() {return dayOfWeek;}

    public void setDate(String date) {
        this.date = date;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getUnits() { return this.units;}
}
