package com.fbr161.buskoi.ui.ticket_purchase_history.backend.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fbr161.buskoi.ui.ticket_purchase_history.backend.model.PastBus;
import com.fbr161.buskoi.ui.ticket_purchase_history.backend.repository.Repository_Ticket_History;

import java.util.ArrayList;
import java.util.List;

public class ViewModel_Ticket_History extends ViewModel {

    MutableLiveData<List<PastBus>> pastBusList_MutableLiveData = new MutableLiveData<>();
    Repository_Ticket_History repository_ticket_history;

    public ViewModel_Ticket_History() {
        this.pastBusList_MutableLiveData.setValue(new ArrayList<>());
        repository_ticket_history = new Repository_Ticket_History();
    }

    public LiveData<List<PastBus>> getPastBusList(String user_phn_no){
        pastBusList_MutableLiveData = (MutableLiveData<List<PastBus>>) repository_ticket_history.getPastBusList(user_phn_no);

        return pastBusList_MutableLiveData;
    }
}
