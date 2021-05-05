package com.fbr161.buskoi.ui.home.view;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.ui.home.backed.model.BookedBus;
import com.fbr161.buskoi.ui.purchase_ticket.backend.model.Bus;
import com.fbr161.buskoi.ui.purchase_ticket.view.searched_bus_list.Search_bus_list_Fragment_recycleView_adapter;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    Context context;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getContext();

        recyclerView = view.findViewById(R.id.home_booked_busList_fragment_booked_bus_list_recycleview);


        ArrayList<BookedBus> bus_list = new ArrayList<>();
        bus_list.add(new BookedBus("Dhaka", "Rajshahi","10:00 PM", "10-10-1995", "Hanif", 500.0, "1A", true, "h1"));
        bus_list.add(new BookedBus("Dhaka", "Rajshahi","10:30 PM", "19-10-1995", "Hanif", 500.0, "1B", true, "h1"));
        bus_list.add(new BookedBus("Dhaka", "Rajshahi","11:00 PM", "15-10-1995", "Hanif", 500.0, "1C", true, "h1"));

        recyclerView.setAdapter(new HomeFragment_Booked_BusList_recycleView_Adapter(bus_list, context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(context));

        return view;
    }

}