package com.fbr161.buskoi.ui.purchase_ticket.backend.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fbr161.buskoi.ui.purchase_ticket.backend.model.Bus;
import com.fbr161.buskoi.ui.purchase_ticket.backend.model.SeatCondition;
import com.fbr161.buskoi.ui.purchase_ticket.backend.repository.Repository_PurchaseTicket;
import com.fbr161.buskoi.ui.purchase_ticket.backend.retrofit.Retrofit_Instanse_PurchaseTicket;

import java.util.List;

public class ViewModel_PurchaseTicket extends ViewModel {

    Repository_PurchaseTicket repository_purchaseTicket;
    private MutableLiveData<List<Bus>> busListLiveData = new MutableLiveData<>();
    private MutableLiveData<String> selected_bus_schedule_id = new MutableLiveData<>();
    private MutableLiveData<SeatCondition> seatConditionLiveData = new MutableLiveData<>();

    public ViewModel_PurchaseTicket() {
        //Log.d("wtf__ViewModel_PurchaseTicket", "ViewModel_PurchaseTicket__before__repository_purchaseTicket__initialization");
        repository_purchaseTicket = new Repository_PurchaseTicket();
        //Log.d("wtf__ViewModel_PurchaseTicket", "ViewModel_PurchaseTicket__after__repository_purchaseTicket__initialization");
        //this.articleResponseLiveData = articleRepository.getMovieArticles(ARTICLE_QUERY, API_KEY);
    }

    public LiveData<List<Bus>> getSearchedBusListLiveData(String frm, String to, String date) {

//        Log.d("wtf__ViewModel_PurchaseTicket", "getSearchedBusListLiveData__before__repository_purchaseTicket.getSearchedBusList");
          busListLiveData = (MutableLiveData<List<Bus>>) repository_purchaseTicket.getSearchedBusList(frm, to, date) ;
//        Log.d("wtf__ViewModel_PurchaseTicket", "getSearchedBusListLiveData__after__repository_purchaseTicket.getSearchedBusList");

        return busListLiveData;
    }

    public LiveData<String> getSelected_bus_schedule_id() {
        return selected_bus_schedule_id;
    }

    public void setSelected_bus_schedule_id(String schedule_id) {
        selected_bus_schedule_id.setValue(schedule_id);
    }

    public LiveData<SeatCondition> getSeatConditionLiveData(String schedule_id) {

        seatConditionLiveData = (MutableLiveData<SeatCondition>) repository_purchaseTicket.getSeatCondition(schedule_id);

        return seatConditionLiveData;
    }
}
