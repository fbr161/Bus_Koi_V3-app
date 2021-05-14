package com.fbr161.buskoi.ui.home.backed.viewmodel;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fbr161.buskoi.ui.home.backed.model.BookedBus;
import com.fbr161.buskoi.ui.home.backed.model.BusLocation;
import com.fbr161.buskoi.ui.home.backed.repository.Repository_BookedBus_and_Tracking__Home;
import java.util.ArrayList;
import java.util.List;

public class ViewModel_BookedBus_Home extends ViewModel {

    Repository_BookedBus_and_Tracking__Home repository_bookedBus_home;
    MutableLiveData<List<BookedBus>> bookedBusList_LiveData = new MutableLiveData<>();
    private MutableLiveData<BusLocation> busLocation_MutableLiveData = new MutableLiveData<>();
    //BookedBus(String from, String to, String dep_time, String dep_date, String company_name, double fare, String booked_seats_no, boolean ac_status, String schedule_id)

    public ViewModel_BookedBus_Home() {
        //Log.d("wtf__ViewModel_PurchaseTicket", "ViewModel_PurchaseTicket__before__repository_purchaseTicket__initialization");
        repository_bookedBus_home = new Repository_BookedBus_and_Tracking__Home();
        bookedBusList_LiveData.postValue(new ArrayList<>());
        busLocation_MutableLiveData.postValue(new BusLocation(0,0));
        //Log.d("wtf__ViewModel_PurchaseTicket", "ViewModel_PurchaseTicket__after__repository_purchaseTicket__initialization");
    }

    public LiveData<List<BookedBus>> getBookedBusList_LiveData(String user_phn_no) {

//        Log.d("wtf__ViewModel_PurchaseTicket", "getSearchedBusListLiveData__before__repository_purchaseTicket.getSearchedBusList");
        bookedBusList_LiveData = (MutableLiveData<List<BookedBus>>) repository_bookedBus_home.getBookededBusList(user_phn_no) ;
//        Log.d("wtf__ViewModel_PurchaseTicket", "getSearchedBusListLiveData__after__repository_purchaseTicket.getSearchedBusList");

        return bookedBusList_LiveData;
    }

    public LiveData<BusLocation> getBusLocation_LiveData() {

        //busLocation_MutableLiveData = (MutableLiveData<BusLocation>)repository_bookedBus_home.getBusLocation(schedule_id);
        return busLocation_MutableLiveData;
    }


//    public void selected_bus_schedule_id(String schedule_id){
//        BusLocation busLocation = busLocation_MutableLiveData.getValue();
//        busLocation.setSchedule_id(schedule_id);
//        busLocation_MutableLiveData.postValue(busLocation);
//    }

    public LiveData<List<BookedBus>> getBookedBusList_LiveData() {
        return bookedBusList_LiveData;
    }


    public void updateLocation(String schedule_id){
        AsyncTask_Bus_Location asyncTask_bus_location = new AsyncTask_Bus_Location(schedule_id);
        asyncTask_bus_location.execute();
    }

    class AsyncTask_Bus_Location extends AsyncTask<Void,Void,Void>{

        String schedule_id;

        public AsyncTask_Bus_Location(String schedule_id) {
            this.schedule_id = schedule_id;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }

        @Override
        protected Void doInBackground(Void... voids) {

            while (true){
                BusLocation bl_repo = repository_bookedBus_home.getBusLocation(schedule_id);;

                //Log.d("wtffffff", bl_repo.getLast_Lat()+", "+ bl_repo.getLast_Lon());

                BusLocation bl_vw = busLocation_MutableLiveData.getValue();
                bl_vw.setLast_Lat_and_Lon(bl_repo.getLast_Lat(), bl_repo.getLast_Lon());

                busLocation_MutableLiveData.postValue(bl_vw);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
