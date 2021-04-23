package com.fbr161.buskoi.ui.purchase_ticket.backend.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fbr161.buskoi.ui.purchase_ticket.backend.model.Bus;
import com.fbr161.buskoi.ui.purchase_ticket.backend.model.SeatCondition;
import com.fbr161.buskoi.ui.purchase_ticket.backend.retrofit.API_PurchaseTicket;
import com.fbr161.buskoi.ui.purchase_ticket.backend.retrofit.Retrofit_Instanse_PurchaseTicket;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository_PurchaseTicket {

    private API_PurchaseTicket api;

    public Repository_PurchaseTicket() {
        //Log.d("wtf__Repository_PurchaseTicket", "Repository_PurchaseTicket__before__api__initialization");
        api = Retrofit_Instanse_PurchaseTicket.getInstance().create(API_PurchaseTicket.class);
        //Log.d("wtf__Repository_PurchaseTicket", "Repository_PurchaseTicket__after__api__initialization");
    }

    // get Searched Bus List
    public LiveData<List<Bus>> getSearchedBusList(String frm, String to, String date) {
        final MutableLiveData<List<Bus>> busList = new MutableLiveData<>();

        //Log.d("wtf__Repository_PurchaseTicket__parameters", frm+", "+to+", "+date);   //"Dhaka","Rajshahi", "2021-04-20"
        api.getSearchedBusList(frm, to, date).enqueue(new Callback<ArrayList<Bus>>() {
            @Override
            public void onResponse(Call<ArrayList<Bus>> call, Response<ArrayList<Bus>> response) {

                //ArrayList<Bus> bus_array = response.body();
                busList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Bus>> call, Throwable t) {
                Log.d("wtf__Repository_PurchaseTicket___ERROR", t.toString());
            }
        });
        //Log.d("wtf__Repository_PurchaseTicket", "getSearchedBusList__after__api.getSearchedBusList");

        return busList;
    }

    // get Seat Condition
    public LiveData<SeatCondition> getSeatCondition(String schedule_id){
        final MutableLiveData<SeatCondition> seatCondition = new MutableLiveData<>();

        api.getSeatCondition(schedule_id).enqueue(new Callback<SeatCondition>() {
            @Override
            public void onResponse(Call<SeatCondition> call, Response<SeatCondition> response) {
                seatCondition.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SeatCondition> call, Throwable t) {
                //Log.d("wtfffffffff api seat condition", t.toString());
            }
        });
        return seatCondition;
    }

    // purchase ticket
    public boolean purchaseTicket(String schedule_id, String user_phn_no, String name, String phn_no, String seat_no, String issued_date_time, double fare, String issued_by){
        final Boolean[] status =  {false};

        api.purchaseTicket(schedule_id, user_phn_no, name, phn_no, seat_no, fare, issued_by).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if(response.body()){
                    status[0]=true;
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Log.d("wtfffffffff api purchase ticket", t.toString());
            }
        });
        return status[0];
    }
}
