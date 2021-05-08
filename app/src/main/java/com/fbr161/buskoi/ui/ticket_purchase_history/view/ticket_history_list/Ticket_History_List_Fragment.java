package com.fbr161.buskoi.ui.ticket_purchase_history.view.ticket_history_list;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.ui.emergency_contacts.backend.model.UsersEmergencyContact;
import com.fbr161.buskoi.ui.emergency_contacts.view.contact_list.Emergency_Contact_List_RecyclerView_Adapter;
import com.fbr161.buskoi.ui.ticket_purchase_history.backend.model.PastBus;

import java.util.ArrayList;

public class Ticket_History_List_Fragment extends Fragment {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket_history_list, container, false);

        recyclerView = view.findViewById(R.id.ticket_history_list_fragment_recyclerView);
        ArrayList<PastBus> pastBusArrayList = new ArrayList<>();
        pastBusArrayList.add(new PastBus("Dhaka", "Rajshahi", "10-10-1995", "10:00", "Hanif", true, 500.0, "1A,2A"));
        pastBusArrayList.add(new PastBus("Dhaka", "Rajshahi", "10-10-1995", "10:00", "Hanif", true, 500.0, "1A,2A"));
        pastBusArrayList.add(new PastBus("Dhaka", "Rajshahi", "10-10-1995", "10:00", "Hanif", true, 500.0, "1A,2A"));

        recyclerView.setAdapter(new Ticket_History_List_Fragment_RecyclerView_Adapter(pastBusArrayList, getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(getContext()));

        return view;
    }
}