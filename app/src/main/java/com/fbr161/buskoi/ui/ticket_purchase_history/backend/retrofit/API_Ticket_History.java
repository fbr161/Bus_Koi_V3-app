package com.fbr161.buskoi.ui.ticket_purchase_history.backend.retrofit;

import com.fbr161.buskoi.constant.Constant;
import com.fbr161.buskoi.ui.home.backed.model.BookedBus;
import com.fbr161.buskoi.ui.home.backed.model.BusLocation;
import com.fbr161.buskoi.ui.ticket_purchase_history.backend.model.PastBus;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API_Ticket_History {

    @FormUrlEncoded
    @POST(Constant.Url.TICKET_HISTORY_API)
    Call<ArrayList<PastBus>> getPastBusList(
            @Field("user_phn_no") String user_phn_no
    );
}
