package com.fbr161.buskoi.ui.home.backed.model;

public class BusLocation{

    double last_Lat;
    double last_Lon;

//    String schedule_id;

    public BusLocation(double last_Lat, double last_Lon) {
        this.last_Lat = last_Lat;
        this.last_Lon = last_Lon;
    }

    public void setLast_Lat_and_Lon(double last_Lat, double last_Lon) {
        this.last_Lat = last_Lat;
        this.last_Lon = last_Lon;
    }

//    public String getSchedule_id() {
//        return schedule_id;
//    }
//
//    public void setSchedule_id(String schedule_id) {
//        this.schedule_id = schedule_id;
//    }

    public double getLast_Lat() {
        return last_Lat;
    }

    public double getLast_Lon() {
        return last_Lon;
    }

    public void setLast_Lat(double last_Lat) {
        this.last_Lat = last_Lat;
    }

    public void setLast_Lon(double last_Lon) {
        this.last_Lon = last_Lon;
    }
}