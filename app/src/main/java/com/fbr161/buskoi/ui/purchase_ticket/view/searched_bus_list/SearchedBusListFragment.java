package com.fbr161.buskoi.ui.purchase_ticket.view.searched_bus_list;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.fbr161.buskoi.R;
import com.fbr161.buskoi.ui.purchase_ticket.backend.model.Bus;
import com.fbr161.buskoi.ui.purchase_ticket.backend.viewmodel.ViewModel_PurchaseTicket;

import java.util.ArrayList;
import java.util.List;


public class SearchedBusListFragment extends Fragment {

    Context context;
    ArrayList<Bus> bus_list_a = new ArrayList<Bus>();
    RecyclerView recyclerView;

    String from_location="";
    String to_location = "";
    String date = "";
    String dayOfWeek = "";

    TextView frm_txtView;
    TextView to_txtView;
    TextView date_txtView;
    ViewModel_PurchaseTicket viewModel_PurchaseTicket;

    public SearchedBusListFragment(String from_location,  String to_location, String date, String dayOfWeek){
        this.from_location = from_location;
        this.to_location = to_location;
        this.date = date;
        this.dayOfWeek = dayOfWeek;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_searched_bus_list, container, false);
        context = getContext();

        frm_txtView = view.findViewById(R.id.searched_bus_list_fragment_from_textView);
        to_txtView = view.findViewById(R.id.searched_bus_list_fragment_to_textView);
        date_txtView = view.findViewById(R.id.searched_bus_list_fragment_date_day_textview);

        frm_txtView.setText(from_location);
        to_txtView.setText(to_location);
        date_txtView.setText(date+"  |  "+dayOfWeek);

       // Toast.makeText(context, from_location+ " "+to_location, Toast.LENGTH_SHORT).show();

        recyclerView = view.findViewById(R.id.search_bus_list_recycleview);

//        bus_list_a.add(new Bus("10:00 PM", "Hanif", 20, 500, false));
//        bus_list_a.add(new Bus("10:00 PM", "Hanif", 25, 500, false));
//        bus_list_a.add(new Bus("11:00 PM", "Dipjol", 10, 450, false));
//        bus_list_a.add(new Bus("12:00 PM", "SR Travels", 35, 400, false));
//                new Bus("05:00 PM", "Elit", 2, 500, false),
//                new Bus("10:00 PM", "Hanif", 25, 500, false),
//                new Bus("11:00 PM", "Dipjol", 10, 450, false),
//                new Bus("12:00 PM", "SR Travels", 35, 400, false),
//                new Bus("05:00 PM", "Elit", 2, 500, false)
//        };

        recyclerView.setAdapter(new Search_bus_list_Fragment_recycleView_adapter(bus_list_a, context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(context));


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Log.d("wtf__SearchedBusListFragment", "onActivityCreated__before__viewModel_PurchaseTicket__initialization");
        viewModel_PurchaseTicket = ViewModelProviders.of(requireActivity()).get(ViewModel_PurchaseTicket.class);
        //Log.d("wtf__SearchedBusListFragment", "onActivityCreated__after_viewModel_PurchaseTicket__initialization");
        viewModel_PurchaseTicket.getSearchedBusListLiveData(from_location, to_location, date).observe(getViewLifecycleOwner(), new Observer<List<Bus>>() {
            @Override
            public void onChanged(List<Bus> buses) {
                recyclerView.setAdapter(new Search_bus_list_Fragment_recycleView_adapter((ArrayList<Bus>)buses, context));
            }
        });



        //Log.d("wtf__SearchedBusListFragment", "onActivityCreated__after__viewModel_PurchaseTicket__observer");
    }

    /////////////////////////


    /////////////////////////
}