package com.fbr161.buskoi.ui.purchase_ticket.backend.model;

import java.util.HashMap;

public class IssueTicket {

    String from="";
    String to="";
    String date="";
    String dayOfWeek="";

    String schedule_id="";

    String dep_time="";
    String company_name="";
    boolean ac_status;
    double fare=0;
    double totalFare = 0;
    int availableSeats = 0;
    String selected_seat_no=" ";

    String user_phn_no="";
    String name="";
    String phn_no="";

    String issued_by="";


    //From To Date DayName
    public HashMap<String, String> getFromToDateDayName(){
        HashMap<String, String> hashMap = new HashMap<String, String>();

        hashMap.put("from",from);
        hashMap.put("to",to);
        hashMap.put("date",date);
        hashMap.put("dayOfWeek",dayOfWeek);

        return hashMap;
    }

    public void setFromToDateDayName(String from, String to, String date, String dayOfWeek) {
        this.from = from;
        this.to = to;
        this.date = date;
        this.dayOfWeek = dayOfWeek;
    }

    //Schedule_id
    public String getSchedule_id() {
        return schedule_id;
    }


    //DepTime CompanyName AcStatus Fare SeatNo
    public void setScheduleId_DepTime_CompanyName_AcStatus_Fare_AvailableSeats(String schedule_id, String dep_time, String company_name, boolean ac_status, double fare, int availableSeats){
        this.schedule_id = schedule_id;
        this.dep_time = dep_time;
        this.company_name = company_name;
        this.ac_status = ac_status;
        this.fare = fare;
        this.availableSeats = availableSeats;
    }

    public HashMap<String, String> getDepTime_CompanyName_AcStatus_Fare_SeatNo(){
        HashMap<String, String> hashMap = new HashMap<String, String>();

        hashMap.put("dep_time",dep_time);
        hashMap.put("company_name",company_name);
        hashMap.put("ac_status",ac_status+"");
        hashMap.put("fare",fare+"");
        hashMap.put("seat_no",selected_seat_no);
        hashMap.put("totalFare",totalFare+"");

        return hashMap;
    }

    //user_phn_no Name Phn_no
    public void setUserPhnNo_Name_PhnNo(String user_phn_no, String name,  String phn_no){
        this.user_phn_no = user_phn_no;
        this.name = name;
        this.phn_no = phn_no;
    }

    public HashMap<String, String> getUserPhnNo_Name_PhnNo(){
        HashMap<String, String> hashMap = new HashMap<String, String>();

        hashMap.put("user_phn_no",user_phn_no);
        hashMap.put("name",name);
        hashMap.put("phn_no",phn_no);

        return hashMap;
    }

    //Issued by
    public String getIssued_by() {
        return issued_by;
    }

    public void setIssued_by(String issued_by) {
        this.issued_by = issued_by;
    }

    //fare
    public double getEachTicketFare() {
        return fare;
    }

    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
    }

    public void setSelectedSeat_no(String seat_no) {
        this.selected_seat_no = seat_no;
    }

    public HashMap<String, String> getSelectedSeatNoAndTotalFare(){
        HashMap<String, String> hashMap = new HashMap<String, String>();

        hashMap.put("selected_seat_no",selected_seat_no);
        hashMap.put("totalFare",""+totalFare);

        return hashMap;
    }

    public String getSelected_seat_no() {
        return selected_seat_no;
    }
}
