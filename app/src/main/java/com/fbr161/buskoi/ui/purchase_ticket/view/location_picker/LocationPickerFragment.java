package com.fbr161.buskoi.ui.purchase_ticket.view.location_picker;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fbr161.buskoi.R;


public class LocationPickerFragment extends Fragment {

    Context context;
    RecyclerView recyclerView;
    String[] citys;

    String from_location="";
    String to_location = "";
    String date = "";
    String from_or_to = "";

    public LocationPickerFragment(String from_location, String to_location, String date, String from_or_to) {
        this.from_location = from_location;
        this.to_location = to_location;
        this.date = date;
        this.from_or_to = from_or_to;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location_picker, container, false);
        context = getContext();

        recyclerView = view.findViewById(R.id.location_picker_fragment_city_list_recyclerview);

        citys = new String[]{"Dhaka", "Rajshahi", "Khulna", "Naogaon"};

        recyclerView.setAdapter(new Location_Picker_Fragment_Recycleview_Adapter(citys, context, from_location, to_location, date, from_or_to));
        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        return view;
    }
}