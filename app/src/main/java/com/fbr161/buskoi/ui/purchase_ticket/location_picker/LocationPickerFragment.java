package com.fbr161.buskoi.ui.purchase_ticket.location_picker;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.clss.Bus;
import com.fbr161.buskoi.ui.home.HomeFragment;
import com.fbr161.buskoi.ui.purchase_ticket.searched_bus_list.Search_bus_list_Fragment_recycleView_adapter;


public class LocationPickerFragment extends Fragment {

    Context context;
    RecyclerView recyclerView;
    String[] citys;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location_picker, container, false);
        context = getContext();

        recyclerView = view.findViewById(R.id.location_picker_fragment_city_list_recyclerview);

        citys = new String[]{"Rajshahi", "Khulna", "Naogaon"};

        recyclerView.setAdapter(new Location_Picker_Fragment_Recycleview_Adapter(citys, context));
        recyclerView.setHasFixedSize(true);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        return view;
    }
}