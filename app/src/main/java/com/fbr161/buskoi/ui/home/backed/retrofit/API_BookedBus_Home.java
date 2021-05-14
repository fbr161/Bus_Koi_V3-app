package com.fbr161.buskoi.ui.home.backed.retrofit;

import com.fbr161.buskoi.constant.Constant;
import com.fbr161.buskoi.ui.home.backed.model.BookedBus;
import com.fbr161.buskoi.ui.home.backed.model.BusLocation;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API_BookedBus_Home {

    @FormUrlEncoded
    @POST(Constant.Url.BOOKED_BUS_LIST_API)
    Call<ArrayList<BookedBus>> getBookedBusList(
            @Field("user_phn_no") String user_phn_no
    );

    @FormUrlEncoded
    @POST(Constant.Url.LIVE_LOCATION_TRACK_API)
    Call<BusLocation> getBusLiveLocation(
            @Field("schedule_id") String schedule_id
    );

}
