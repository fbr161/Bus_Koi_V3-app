package com.fbr161.buskoi.ui.purchase_ticket.seat_selection;

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
import com.fbr161.buskoi.clss.Bus_Seat_Plan;
import com.fbr161.buskoi.ui.purchase_ticket.searched_bus_list.Search_bus_list_Fragment_recycleView_adapter;


public class SeatSelectionFragment extends Fragment {

    Context context;
    boolean[][] bus_Seat_Plan = new Bus_Seat_Plan("1").getSeat();
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_seat_selection, container, false);
        context = getContext();

        recyclerView = view.findViewById(R.id.seat_selection_fragment_recycleview);


        recyclerView.setAdapter(new Seat_Selection_Fragment_Seat_Plan_RecycleView_Adapter(bus_Seat_Plan, context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(context));

        return view;
    }
}