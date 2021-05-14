package com.fbr161.buskoi.constant;

public class Constant {

    //SignUp & SignIn

    private Constant(){}

    //Location_picker
    public final static String  LOCATION_PICKER_FROM = "from";
    public final static String  LOCATION_PICKER_TO = "to";

    public class SharedPreferences{
        public final static String  USER_INFO_SHARED_PREF = "user_info_shared_pref";
        public final static String  KEY___USER_PHONE_NO = "phn_no";
        public final static String  KEY___USER_NAME = "name";
        public final static String  KEY___USER_GENDER = "gender";

    }


    public class Url{

        public final static String API_SERVER = "http://192.168.0.4/";
        public final static String BASE_URL = API_SERVER+"Bus_Koi/api/api/post/bus_koi_app/";

        //Purchase Ticket
        public final static String PURCHASE_TICKET_BASE_URL = BASE_URL+"purchase_ticket/";

        public final static String SEARCHED_BUS_LIST_API = "api_search_buses_by_location_and_date.php";
        public final static String SEAT_CONDITION_API = "api_get_seat_condition.php";
        public final static String PURCHASE_TICKET_API = "api_purchase_ticket.php";

        //booked_bus_list__and__bus_tracking
        public final static String BOOKED_BUS_LIST__AND__BUS_TRACKING_BASE_URL = BASE_URL+"booked_bus_list__and__bus_tracking/";

        public final static String BOOKED_BUS_LIST_API = "api_booked_bus_list.php";
        public final static String LIVE_LOCATION_TRACK_API = "api_get_bus_live_location.php";


    }

}
