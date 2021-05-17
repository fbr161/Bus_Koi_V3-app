package com.fbr161.buskoi.ui.home.backed.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fbr161.buskoi.ui.home.backed.model.BookedBus;
import com.fbr161.buskoi.ui.home.backed.model.BusLocation;
import com.fbr161.buskoi.ui.home.backed.retrofit.API_BookedBus_Home;
import com.fbr161.buskoi.ui.home.backed.retrofit.Retrofit_Instance_BookedBus_Home;
import com.fbr161.buskoi.ui.purchase_ticket.backend.model.Bus;
import com.fbr161.buskoi.ui.purchase_ticket.backend.retrofit.API_PurchaseTicket;
import com.fbr161.buskoi.ui.purchase_ticket.backend.retrofit.Retrofit_Instanse_PurchaseTicket;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository_BookedBus_and_Tracking__Home {

    private API_BookedBus_Home api;

    public Repository_BookedBus_and_Tracking__Home() {
        api = Retrofit_Instance_BookedBus_Home.getInstance().create(API_BookedBus_Home.class);
    }

    public LiveData<List<BookedBus>> getBookededBusList(String user_phn_no) {
        final MutableLiveData<List<BookedBus>> busList = new MutableLiveData<>();

        //Log.d("wtf__Repository_PurchaseTicket__parameters", frm+", "+to+", "+date);   //"Dhaka","Rajshahi", "2021-04-20"
        api.getBookedBusList(user_phn_no).enqueue(new Callback<ArrayList<BookedBus>>() {

            @Override
            public void onResponse(Call<ArrayList<BookedBus>> call, Response<ArrayList<BookedBus>> response) {
                busList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<BookedBus>> call, Throwable t) {
                Log.d("wtf__Repository_BookedBus_getBookededBusList___ERROR", t.toString());
            }
        });

        return busList;
    }


    double lat=0,lon=0;
    public BusLocation getBusLocation(String schedule_id){

        api.getBusLiveLocation(schedule_id).enqueue(new Callback<BusLocation>() {
            @Override
            public void onResponse(Call<BusLocation> call, Response<BusLocation> response) {
                //busLocationMutableLiveData.postValue(response.body());
                //Log.d("wtffffff_repo", response.body().getLast_Lat()+", "+ response.body().getLast_Lon());
                //bl.setLast_Lat_and_Lon(response.body().getLast_Lat(), response.body().getLast_Lon());
                //bl[0] = new BusLocation(response.body().getLast_Lat(),response.body().getLast_Lon());
                lat = response.body().getLast_Lat();
                lon = response.body().getLast_Lon();
            }

            @Override
            public void onFailure(Call<BusLocation> call, Throwable t) {
                Log.d("wtf__Repository_BookedBus_getBusLocation___ERROR", t.toString());
            }


        });
        return new BusLocation(lat, lon);
    }
}
