package com.fbr161.buskoi.ui.ticket_purchase_history.backend.model;

public class PastBus {
    String from;
    String to;
    String date;
    String time;
    String company_name;
    boolean ac_status;
    double fare;
    String seat_no;

    public PastBus(String from, String to, String date, String time, String company_name, boolean ac_status, double fare, String seat_no) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.time = time;
        this.company_name = company_name;
        this.ac_status = ac_status;
        this.fare = fare;
        this.seat_no = seat_no;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getCompany_name() {
        return company_name;
    }

    public boolean getAc_status() {
        return ac_status;
    }

    public double getFare() {
        return fare;
    }

    public String getSeat_no() {
        return seat_no;
    }
}
