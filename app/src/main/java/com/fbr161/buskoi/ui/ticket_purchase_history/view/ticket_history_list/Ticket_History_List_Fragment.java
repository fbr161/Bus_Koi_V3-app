package com.fbr161.buskoi.ui.ticket_purchase_history.view.ticket_history_list;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.constant.Constant;
import com.fbr161.buskoi.ui.home.backed.viewmodel.ViewModel_BookedBus_Home;
import com.fbr161.buskoi.ui.ticket_purchase_history.backend.model.PastBus;
import com.fbr161.buskoi.ui.ticket_purchase_history.backend.viewModel.ViewModel_Ticket_History;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class Ticket_History_List_Fragment extends Fragment {

    RecyclerView recyclerView;

    ViewModel_Ticket_History viewModel_ticket_history;

    String user_name;
    String user_phn_no;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket_history_list, container, false);
        viewModel_ticket_history = ViewModelProviders.of(requireActivity()).get(ViewModel_Ticket_History.class);

        recyclerView = view.findViewById(R.id.ticket_history_list_fragment_recyclerView);


        /////////////////////////Shared preference
        SharedPreferences user_info_pref = getContext().getApplicationContext().getSharedPreferences(Constant.SharedPreferences.USER_INFO_SHARED_PREF, MODE_PRIVATE); // 0 - for private mode
        user_name = user_info_pref.getString(Constant.SharedPreferences.KEY___USER_NAME, "");
        user_phn_no = user_info_pref.getString(Constant.SharedPreferences.KEY___USER_PHONE_NO, "");

        viewModel_ticket_history.getPastBusList(user_phn_no).observe(getViewLifecycleOwner(), new Observer<List<PastBus>>() {
            @Override
            public void onChanged(List<PastBus> pastBuses) {
                ArrayList<PastBus> pastBusArrayList = (ArrayList<PastBus>) pastBuses;

                recyclerView.setAdapter(new Ticket_History_List_Fragment_RecyclerView_Adapter(pastBusArrayList, getContext()));
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager( new LinearLayoutManager(getContext()));
            }
        });

        return view;
    }
}