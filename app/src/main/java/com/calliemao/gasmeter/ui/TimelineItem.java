package com.calliemao.gasmeter.ui;

/**
 * Created by alex on 11/7/15.
 */
public class TimelineItem {

    String date;
    String distance;

    TimelineItem(String date, String distance){
        this.date = date;
        this.distance = distance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
