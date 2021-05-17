package com.fbr161.buskoi.ui.ticket_purchase_history.backend.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fbr161.buskoi.ui.home.backed.model.BookedBus;
import com.fbr161.buskoi.ui.home.backed.retrofit.API_BookedBus_Home;
import com.fbr161.buskoi.ui.home.backed.retrofit.Retrofit_Instance_BookedBus_Home;
import com.fbr161.buskoi.ui.ticket_purchase_history.backend.model.PastBus;
import com.fbr161.buskoi.ui.ticket_purchase_history.backend.retrofit.API_Ticket_History;
import com.fbr161.buskoi.ui.ticket_purchase_history.backend.retrofit.Retrofit_InstanceI_Ticket_History;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository_Ticket_History {

    private API_Ticket_History api;

    public Repository_Ticket_History() {
        api = Retrofit_InstanceI_Ticket_History.getInstance().create(API_Ticket_History.class);
    }

    public LiveData<List<PastBus>> getPastBusList(String user_phn_no) {

        final MutableLiveData<List<PastBus>> busList = new MutableLiveData<>();

        //Log.d("wtf__Repository_PurchaseTicket__parameters", frm+", "+to+", "+date);   //"Dhaka","Rajshahi", "2021-04-20"
        api.getPastBusList(user_phn_no).enqueue(new Callback<ArrayList<PastBus>>() {
            @Override
            public void onResponse(Call<ArrayList<PastBus>> call, Response<ArrayList<PastBus>> response) {
                busList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<PastBus>> call, Throwable t) {
                Log.d("wtf_getPastBusList", t.toString());
            }
        });

        return busList;
    }
}
