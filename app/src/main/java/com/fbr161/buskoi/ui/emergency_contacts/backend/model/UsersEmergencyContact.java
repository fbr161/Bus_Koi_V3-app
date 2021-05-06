package com.fbr161.buskoi.ui.emergency_contacts.backend.model;

public class UsersEmergencyContact {

    String name;
    String phn_no;

    public UsersEmergencyContact(String name, String phn_no) {
        this.name = name;
        this.phn_no = phn_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhn_no() {
        return phn_no;
    }

    public void setPhn_no(String phn_no) {
        this.phn_no = phn_no;
    }
}
