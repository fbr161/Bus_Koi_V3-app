package com.fbr161.buskoi.ui.home.backed.retrofit;

import com.fbr161.buskoi.constant.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit_Instance_BookedBus_Home {

    private static Retrofit retrofit;


    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.Url.BOOKED_BUS_LIST__AND__BUS_TRACKING_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
