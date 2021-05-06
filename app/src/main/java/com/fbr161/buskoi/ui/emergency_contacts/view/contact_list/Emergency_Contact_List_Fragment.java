package com.fbr161.buskoi.ui.emergency_contacts.view.contact_list;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.ui.emergency_contacts.backend.model.UsersEmergencyContact;
import com.fbr161.buskoi.ui.purchase_ticket.view.searched_bus_list.Search_bus_list_Fragment_recycleView_adapter;

import java.util.ArrayList;


public class Emergency_Contact_List_Fragment extends Fragment {


    Context context;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_emergency_contact_list, container, false);
        context = getContext();

        recyclerView = view.findViewById(R.id.emergency_contact_list_fragment_recyclerView);

        ArrayList<UsersEmergencyContact> usersEmergencyContacts = new ArrayList<>();
        usersEmergencyContacts.add(new UsersEmergencyContact("fuad", "01739"));
        usersEmergencyContacts.add(new UsersEmergencyContact("bin", "01770"));
        usersEmergencyContacts.add(new UsersEmergencyContact("rahman", "01730"));

        recyclerView.setAdapter(new Emergency_Contact_List_RecyclerView_Adapter(usersEmergencyContacts, context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(context));

        return view;
    }
}