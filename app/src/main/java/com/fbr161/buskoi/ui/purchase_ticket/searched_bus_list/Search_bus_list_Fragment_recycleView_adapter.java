package com.fbr161.buskoi.ui.purchase_ticket.searched_bus_list;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.clss.Bus;
import com.fbr161.buskoi.ui.purchase_ticket.seat_selection.SeatSelectionFragment;

public class Search_bus_list_Fragment_recycleView_adapter extends RecyclerView.Adapter<Search_bus_list_Fragment_recycleView_adapter.Holder> {

    Bus[] bus_lists;
    Context context;

    public Search_bus_list_Fragment_recycleView_adapter(Bus[] bus_lists, Context context) {
        this.bus_lists = bus_lists;
        this.context = context;
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

        final String depr_time = bus_lists[i].getDepr_time();
        final String company_name = bus_lists[i].getCompany_name();
        final int available_seat = bus_lists[i].getAvailable_seat();
        final double fare = bus_lists[i].getFare();
        String ac_status = "";

        if (bus_lists[i].getAcStatus()){
            ac_status = "A/C";
        }else { ac_status = "Non A/C";}


        holder.dept_time_textView.setText(depr_time);
        holder.company_name_textView.setText(company_name);
        holder.fare_textView.setText("৳ "+fare);
        holder.available_seat_textView.setText(available_seat+" seats");
        holder.ac_status_textView.setText(ac_status);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, fare+"", Toast.LENGTH_SHORT).show();
                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container, new SeatSelectionFragment()).addToBackStack(null).commit();
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
        return bus_lists.length;
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
