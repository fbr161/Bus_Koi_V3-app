package com.fbr161.buskoi.ui.purchase_ticket.backend.model;

public class SeatCondition {

    String schedule_id;
    private boolean[][] seat_condition;
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


    public SeatCondition(String schedule_id, boolean[][] seat_condition) {
        this.schedule_id = schedule_id;
        this.seat_condition = seat_condition;
    }

    public String getSchedule_id() {
        return schedule_id;
    }

    public boolean[][] getSeat_condition() {
        return seat_condition;
    }
}
