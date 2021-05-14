package com.fbr161.buskoi.ui.home.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.ui.home.backed.model.BookedBus;
import com.fbr161.buskoi.ui.home.backed.viewmodel.ViewModel_BookedBus_Home;
import com.fbr161.buskoi.ui.home.view.map.MapBusTrackingFragment;
import com.fbr161.buskoi.ui.purchase_ticket.backend.model.Bus;
import com.fbr161.buskoi.ui.purchase_ticket.backend.viewmodel.ViewModel_PurchaseTicket;
import com.fbr161.buskoi.ui.purchase_ticket.view.searched_bus_list.Search_bus_list_Fragment_recycleView_adapter;
import com.fbr161.buskoi.ui.purchase_ticket.view.seat_selection.SeatSelectionFragment;

import java.util.ArrayList;

public class HomeFragment_Booked_BusList_recycleView_Adapter extends RecyclerView.Adapter<HomeFragment_Booked_BusList_recycleView_Adapter.Holder>{

    ArrayList<BookedBus> bus_lists = new ArrayList<BookedBus>();
    Context context;
    //ViewModel_BookedBus_Home viewModel_bookedBus_home;

    ViewModel_BookedBus_Home viewModel_bookedBus_home;

    public HomeFragment_Booked_BusList_recycleView_Adapter(ArrayList<BookedBus> bus_lists, Context context, ViewModel_BookedBus_Home viewModel_bookedBus_home){ //, ViewModel_BookedBus_Home viewModel_bookedBus_home) {
        this.bus_lists = bus_lists;
        this.context = context;
        this.viewModel_bookedBus_home = viewModel_bookedBus_home;
       // this.viewModel_bookedBus_home = viewModel_bookedBus_home;
    }

    @NonNull
    @Override
    public HomeFragment_Booked_BusList_recycleView_Adapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.recycle_item_booked_buses_list, viewGroup, false);

        return new HomeFragment_Booked_BusList_recycleView_Adapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeFragment_Booked_BusList_recycleView_Adapter.Holder holder, int i) {

        final String schedule_id = bus_lists.get(i).getSchedule_id();

        final String depr_time = bus_lists.get(i).getDep_time();
        final String depr_date = bus_lists.get(i).getDep_date();
        final String company_name = bus_lists.get(i).getCompany_name();
        final String booked_seat_no = bus_lists.get(i).getBooked_seats_no();
        final double fare = bus_lists.get(i).getFare();
        String ac_status = "";

        final int indx = i;

        boolean ac_status_boolean = bus_lists.get(i).getAc_status();
        if (ac_status_boolean){
            ac_status = "A/C";
        }else { ac_status = "Non A/C";}

        holder.dept_time_textView.setText(depr_time);
        holder.dept_date_textView.setText(depr_date);
        holder.company_name_textView.setText(company_name);

        holder.fare_textView.setText("৳"+fare);
        holder.booked_seat_no_textView.setText(booked_seat_no);
        holder.ac_status_textView.setText(ac_status);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, fare+"", Toast.LENGTH_SHORT).show();

                Bundle arg = new Bundle();
                arg.putInt("index", indx);
                MapBusTrackingFragment mapBusTrackingFragment = new MapBusTrackingFragment();
                mapBusTrackingFragment.setArguments(arg);

//                viewModel_bookedBus_home.selected_bus_schedule_id(schedule_id);
                //viewModel_PurchaseTicket.setScheduleId_DepTime_CompanyName_AcStatus_Fare_AvailableSeats(schedule_id, depr_time, company_name, ac_status_boolean, fare, available_seat);

                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container, mapBusTrackingFragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return bus_lists.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView dept_time_textView;
        TextView dept_date_textView;
        TextView company_name_textView;
        TextView fare_textView;
        TextView booked_seat_no_textView;
        TextView ac_status_textView;
        CardView container;

        public Holder(@NonNull View itemView) {
            super(itemView);
            dept_time_textView = itemView.findViewById(R.id.booked_bus_list_recycleview_item_time);
            dept_date_textView = itemView.findViewById(R.id.booked_bus_list_recycleview_item_date);
            company_name_textView = itemView.findViewById(R.id.booked_bus_list_recycleview_item_company_name);
            fare_textView = itemView.findViewById(R.id.booked_bus_list_recycleview_item_fare);
            booked_seat_no_textView = itemView.findViewById(R.id.booked_bus_list_recycleview_item_seat_no);
            ac_status_textView = itemView.findViewById(R.id.booked_bus_list_recycleview_item_ac_status);
            container = itemView.findViewById(R.id.booked_bus_list_recycleview_item_container);
        }
    }
}
