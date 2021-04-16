package com.fbr161.buskoi.clss;

public class Bus_Seat_Plan {

    String bus_id;
    private boolean[][] seat = {{true, false, true, false },
                                {true, false, true, false },
                                {false, true, false, false },
                                {true, false, true, false },
                                {true, false, true, true },
                                {false, true, true, false },
                                {true, false, true, false },
                                {true, false, false, true },
                                {false, true, true, false },
                                {true, false, true, false }};

    public Bus_Seat_Plan(String bus_id) {
        this.bus_id = bus_id;
    }

    public boolean[][] getSeat() {
        return seat;
    }
}
