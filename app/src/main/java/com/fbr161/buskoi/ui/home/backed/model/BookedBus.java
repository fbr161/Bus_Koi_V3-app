package com.fbr161.buskoi.ui.home.backed.model;

public class BookedBus {

    String from, to;

    String dep_time;
    String dep_date;
    String company_name;
    String booked_seats_no;
    double fare;
    boolean ac_status;

    String schedule_id;

    BusLocation busLocation;


    public BookedBus(String from, String to, String dep_time, String dep_date, String company_name, double fare, String booked_seats_no, boolean ac_status, String schedule_id) {
        this.from = from;
        this.to = to;
        this.dep_time = dep_time;
        this.dep_date = dep_date;
        this.company_name = company_name;
        this.booked_seats_no = booked_seats_no;
        this.fare = fare;
        this.ac_status = ac_status;
        this.schedule_id = schedule_id;

        busLocation = new BusLocation(0,0);
    }

    public String getDep_time() {
        return dep_time;
    }

    public String getDep_date() {
        return dep_date;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getBooked_seats_no() {
        return booked_seats_no;
    }

    public double getFare() {
        return fare;
    }

    public boolean getAc_status() {
        return ac_status;
    }

    public String getSchedule_id() {
        return schedule_id;
    }


    public String getFrom_Location() {
        return from;
    }

    public String getTo_Location() {
        return to;
    }

    public BusLocation getBusLocation() {
        return busLocation;
    }

    public void setBusLocation(double lat, double lon) {
        busLocation.setLast_Lat_and_Lon(lat, lon);
    }
}
