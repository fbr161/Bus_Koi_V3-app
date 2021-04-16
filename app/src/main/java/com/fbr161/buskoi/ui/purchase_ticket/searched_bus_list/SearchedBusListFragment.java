package com.fbr161.buskoi.ui.purchase_ticket.searched_bus_list;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.clss.Bus;


public class SearchedBusListFragment extends Fragment {

    Context context;
    Bus[] bus_list;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_searched_bus_list, container, false);
        context = getContext();
        recyclerView = view.findViewById(R.id.search_bus_list_recycleview);

        bus_list = new Bus[]{new Bus("10:00 PM", "Hanif", 20, 500, false),
                new Bus("10:00 PM", "Hanif", 25, 500, false),
                new Bus("11:00 PM", "Dipjol", 10, 450, false),
                new Bus("12:00 PM", "SR Travels", 35, 400, false),
                new Bus("05:00 PM", "Elit", 2, 500, false),
                new Bus("10:00 PM", "Hanif", 25, 500, false),
                new Bus("11:00 PM", "Dipjol", 10, 450, false),
                new Bus("12:00 PM", "SR Travels", 35, 400, false),
                new Bus("05:00 PM", "Elit", 2, 500, false)
        };

        recyclerView.setAdapter(new Search_bus_list_Fragment_recycleView_adapter(bus_list, context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(context));

        return view;
    }
}