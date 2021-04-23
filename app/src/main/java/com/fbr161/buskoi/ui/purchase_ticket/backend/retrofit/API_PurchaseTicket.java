package com.fbr161.buskoi.ui.purchase_ticket.backend.retrofit;

import com.fbr161.buskoi.constant.Constant;
import com.fbr161.buskoi.ui.purchase_ticket.backend.model.Bus;
import com.fbr161.buskoi.ui.purchase_ticket.backend.model.SeatCondition;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API_PurchaseTicket {

    @FormUrlEncoded
    @POST(Constant.Url.SEARCHED_BUS_LIST_API)
    Call<ArrayList<Bus>> getSearchedBusList(
            @Field("from") String from,
            @Field("to") String to,
            @Field("date") String date
    );

    @FormUrlEncoded
    @POST(Constant.Url.SEAT_CONDITION_API)
    Call<SeatCondition> getSeatCondition(
            @Field("schedule_id") String schedule_id
    );

    @FormUrlEncoded
    @POST(Constant.Url.PURCHASE_TICKET_API )
    Call<Boolean> purchaseTicket(
            @Field("schedule_id") String schedule_id,
            @Field("user_phn_no") String user_phn_no,
            @Field("name") String name,
            @Field("phn_no") String phn_no,
            @Field("seat_no") String seat_no,
            @Field("fare") double fare,
            @Field("issued_by") String issued_by
    );

}
