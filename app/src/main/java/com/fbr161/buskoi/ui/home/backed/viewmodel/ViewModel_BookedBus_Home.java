package com.fbr161.buskoi.ui.home.backed.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fbr161.buskoi.ui.home.backed.model.BookedBus;
import com.fbr161.buskoi.ui.home.backed.repository.Repository_BookedBus_Home;

import java.util.List;

public class ViewModel_BookedBus_Home extends ViewModel {

    Repository_BookedBus_Home repository_bookedBus_home;
    MutableLiveData<List<BookedBus>> bookedBusList_LiveData = new MutableLiveData<>();




}
