package com.fbr161.buskoi.ui.purchase_ticket.backend.model;

public class SeatCondition {

    String schedule_id;
    private boolean[][] seat_condition;
    private String user_previous_booked_seat_record;
//    private boolean[][] seat = {{true, false, true, false },
//                                {true, false, true, false },
//                                {false, true, false, false },
//                                {true, false, true, false },
//                                {true, false, true, true },
//                                {false, true, true, false },
//                                {true, false, true, false },
//                                {true, false, false, true },
//                                {false, true, true, false },
//                                {true, false, true, false }};


    public SeatCondition(String schedule_id, boolean[][] seat_condition, String user_previous_booked_seat_record) {
        this.schedule_id = schedule_id;
        this.seat_condition = seat_condition;
        this.user_previous_booked_seat_record = user_previous_booked_seat_record;
    }

    public String getSchedule_id() {
        return schedule_id;
    }

    public boolean[][] getSeat_condition() {
        return seat_condition;
    }

    public String getUser_previous_booked_seat_record() {
        return user_previous_booked_seat_record;
    }
}
