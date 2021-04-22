package com.fbr161.buskoi.ui.purchase_ticket.backend.model;

public class Bus {
    String company_name;
    String dep_time;
//    String arraival_time;
    int available_seats;
    double fare;
    boolean ac_status;
    String schedule_id;

    public Bus(String company_name, String dep_time, int available_seats, double fare, boolean ac_status, String schedule_id) {
        this.company_name = company_name;
        this.dep_time = dep_time;
//        this.arraival_time = arraival_time;
        this.available_seats = available_seats;
        this.fare = fare;
        this.ac_status = ac_status;
        this.schedule_id = schedule_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getDepr_time() {
        return dep_time;
    }

//    public String getArraival_time() {
//        return arraival_time;
//    }

    public int getAvailable_seat() {
        return available_seats;
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

}
