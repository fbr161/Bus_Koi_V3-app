package com.fbr161.buskoi.ui.purchase_ticket.view.searched_bus_list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.ui.purchase_ticket.backend.model.Bus;
import com.fbr161.buskoi.ui.purchase_ticket.backend.viewmodel.ViewModel_PurchaseTicket;
import com.fbr161.buskoi.ui.purchase_ticket.view.seat_selection.SeatSelectionFragment;

import java.util.ArrayList;

public class Search_bus_list_Fragment_recycleView_adapter extends RecyclerView.Adapter<Search_bus_list_Fragment_recycleView_adapter.Holder> {

    ArrayList<Bus> bus_lists = new ArrayList<Bus>();
    Context context;
    ViewModel_PurchaseTicket viewModel_PurchaseTicket;

    public Search_bus_list_Fragment_recycleView_adapter(ArrayList<Bus> bus_lists, Context context, ViewModel_PurchaseTicket viewModel_PurchaseTicket) {
        this.bus_lists = bus_lists;
        this.context = context;
        this.viewModel_PurchaseTicket = viewModel_PurchaseTicket;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.search_bus_list_recycleview_item, viewGroup, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {

        final String schedule_id = bus_lists.get(i).getSchedule_id();
        final String depr_time = bus_lists.get(i).getDepr_time();
        final String company_name = bus_lists.get(i).getCompany_name();
        final int available_seat = bus_lists.get(i).getAvailable_seat();
        final double fare = bus_lists.get(i).getFare();
        String ac_status = "";

        final int indx = i;

        boolean ac_status_boolean = bus_lists.get(i).getAc_status();
        if (ac_status_boolean){
            ac_status = "A/C";
        }else { ac_status = "Non A/C";}

        holder.dept_time_textView.setText(depr_time);
        holder.company_name_textView.setText(company_name);
        holder.fare_textView.setText("৳"+fare);
        holder.available_seat_textView.setText(available_seat+" seats");
        holder.ac_status_textView.setText(ac_status);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, fare+"", Toast.LENGTH_SHORT).show();

                Bundle arg = new Bundle();
                arg.putInt("index", indx);
                SeatSelectionFragment seatSelectionFragment = new SeatSelectionFragment();
                seatSelectionFragment.setArguments(arg);

                viewModel_PurchaseTicket.setScheduleId_DepTime_CompanyName_AcStatus_Fare_AvailableSeats(schedule_id, depr_time, company_name, ac_status_boolean, fare, available_seat);

                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container, seatSelectionFragment).addToBackStack(context.getString(R.string.eTicketing_fragment_backStack_name)).commit();
            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                Intent intent = new Intent(context, SeatSelectionFragment.class);
////                intent.putExtra(XTRA_KEY_NAME, author);
////                intent.putExtra(XTRA_KEY_DATE_TIME, date+" "+dep_time);
////                intent.putExtra(XTRA_KEY_BUS_NO, bus_no);
//
////                context.startActivity(intent);
//
//                AppCompatActivity activity = (AppCompatActivity)view.getContext();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container, new SeatSelectionFragment()).addToBackStack(null).commit();
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return bus_lists.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView dept_time_textView;
        TextView company_name_textView;
        TextView fare_textView;
        TextView available_seat_textView;
        TextView ac_status_textView;
        CardView container;

        public Holder(@NonNull View itemView) {
            super(itemView);
            dept_time_textView = itemView.findViewById(R.id.search_bus_list_recycleview_item_time);
            company_name_textView = itemView.findViewById(R.id.search_bus_list_recycleview_item_company_name);
            fare_textView = itemView.findViewById(R.id.search_bus_list_recycleview_item_fare);
            available_seat_textView = itemView.findViewById(R.id.search_bus_list_recycleview_item_available_seat);
            ac_status_textView = itemView.findViewById(R.id.search_bus_list_recycleview_item_ac_status);
            container = itemView.findViewById(R.id.search_bus_list_recycleview_item_container);
        }
    }
}
