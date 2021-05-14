package com.fbr161.buskoi.ui.home.view;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.constant.Constant;
import com.fbr161.buskoi.ui.home.backed.model.BookedBus;
import com.fbr161.buskoi.ui.home.backed.viewmodel.ViewModel_BookedBus_Home;
import com.fbr161.buskoi.ui.purchase_ticket.backend.model.Bus;
import com.fbr161.buskoi.ui.purchase_ticket.backend.viewmodel.ViewModel_PurchaseTicket;
import com.fbr161.buskoi.ui.purchase_ticket.view.searched_bus_list.Search_bus_list_Fragment_recycleView_adapter;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    Context context;
    RecyclerView recyclerView;

    ViewModel_BookedBus_Home viewModel_bookedBus_home;

    String user_phn_no;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getContext();
        viewModel_bookedBus_home = ViewModelProviders.of(requireActivity()).get(ViewModel_BookedBus_Home.class);

        SharedPreferences user_info_pref = context.getApplicationContext().getSharedPreferences(Constant.SharedPreferences.USER_INFO_SHARED_PREF, MODE_PRIVATE); // 0 - for private mode
        user_phn_no = user_info_pref.getString(Constant.SharedPreferences.KEY___USER_PHONE_NO, "");

        recyclerView = view.findViewById(R.id.home_booked_busList_fragment_booked_bus_list_recycleview);


        ArrayList<BookedBus> bus_list = new ArrayList<>();
//        bus_list.add(new BookedBus("Dhaka", "Rajshahi","10:00 PM", "10-10-1995", "Hanif", 500.0, "1A", true, "h1"));
//        bus_list.add(new BookedBus("Dhaka", "Rajshahi","10:30 PM", "19-10-1995", "Hanif", 500.0, "1B", true, "h1"));
//        bus_list.add(new BookedBus("Dhaka", "Rajshahi","11:00 PM", "15-10-1995", "Hanif", 500.0, "1C", true, "h1"));

        viewModel_bookedBus_home.getBookedBusList_LiveData(user_phn_no).observe(getViewLifecycleOwner(), new Observer<List<BookedBus>>() {
            @Override
            public void onChanged(List<BookedBus> bookedBuses) {
                recyclerView.setAdapter(new HomeFragment_Booked_BusList_recycleView_Adapter((ArrayList<BookedBus>)bookedBuses, context, viewModel_bookedBus_home));
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager( new LinearLayoutManager(context));
            }
        });


        return view;
    }

}