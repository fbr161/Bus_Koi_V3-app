package com.fbr161.buskoi.constant;

public class Constant {

    //SignUp & SignIn
    public final static String  LOG_IN_SHARED_PREF = "logIn_shared_pref";
    public final static String  SHARED_PREF_KEY___LOGIN_STATUS = "logIn_status";
    public final static String  SHARED_PREF_KEY___PHONE_NO = "phn_no";
    public final static String  SHARED_PREF_KEY___NAME = "name";
    public final static String  SHARED_PREF_KEY___server = "server";

    //Location_picker
    public final static String  LOCATION_PICKER_FROM = "from";
    public final static String  LOCATION_PICKER_TO = "to";

    public class Url{

        public final static String API_SERVER = "http://192.168.0.4/";
        public final static String BASE_URL = API_SERVER+"/Bus_Koi/api/api/post/";

        //Purchase Ticket
        public final static String SEARCHED_BUS_LIST_API = "api_search_buses_by_location_and_date.php";
        public final static String SEAT_CONDITION_API = "api_get_seat_condition.php";
        public final static String PURCHASE_TICKET_API = "api_purchase_ticket.php";


    }

}
