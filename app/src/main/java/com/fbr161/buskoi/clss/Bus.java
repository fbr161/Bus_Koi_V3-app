package com.fbr161.buskoi.clss;

public class Bus {
    String depr_time, company_name;
    int available_seat;
    double fare;
    boolean ac_status;

    public Bus(String depr_time, String company_name, int available_seat, double fare, boolean ac_status) {
        this.depr_time = depr_time;
        this.company_name = company_name;
        this.available_seat = available_seat;
        this.fare = fare;
        this.ac_status = ac_status;
    }

    public String getDepr_time() {
        return depr_time;
    }

    public String getCompany_name() {
        return company_name;
    }

    public int getAvailable_seat() {
        return available_seat;
    }

    public double getFare() {
        return fare;
    }

    public boolean getAcStatus() {
        return ac_status;
    }

}
