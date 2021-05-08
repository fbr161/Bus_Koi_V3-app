package com.fbr161.buskoi.ui.ticket_purchase_history.view.ticket_history_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.ui.emergency_contacts.backend.model.UsersEmergencyContact;
import com.fbr161.buskoi.ui.emergency_contacts.view.contact_list.Emergency_Contact_List_RecyclerView_Adapter;
import com.fbr161.buskoi.ui.ticket_purchase_history.backend.model.PastBus;

import java.util.ArrayList;

public class Ticket_History_List_Fragment_RecyclerView_Adapter extends RecyclerView.Adapter<Ticket_History_List_Fragment_RecyclerView_Adapter.Holder>{

    ArrayList<PastBus> pastBusArrayList = new ArrayList<>();
    Context context;

    public Ticket_History_List_Fragment_RecyclerView_Adapter(ArrayList<PastBus> pastBusArrayList, Context context) {
        this.pastBusArrayList = pastBusArrayList;
        this.context = context;

    }

    @NonNull
    @Override
    public Ticket_History_List_Fragment_RecyclerView_Adapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.recycler_item_ticket_history_list, viewGroup, false);

        return new Ticket_History_List_Fragment_RecyclerView_Adapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Ticket_History_List_Fragment_RecyclerView_Adapter.Holder holder, int i) {

        String from;
        String to;
        String date = pastBusArrayList.get(i).getDate();
        String time = pastBusArrayList.get(i).getTime();
        String company_name = pastBusArrayList.get(i).getCompany_name();
        String ac_status = pastBusArrayList.get(i).getAc_status() ? "A/C":"Non A/C";
        double fare = pastBusArrayList.get(i).getFare();
        String seat_no = pastBusArrayList.get(i).getSeat_no();

        holder.dept_date_textView.setText(date);
        holder.dept_time_textView.setText(time);
        holder.company_name_textView.setText(company_name);
        holder.ac_status_textView.setText(ac_status);
        holder.fare_textView.setText("৳"+fare);
        holder.seat_no_textView.setText(seat_no);


    }

    @Override
    public int getItemCount() {
        return pastBusArrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView dept_date_textView;
        TextView dept_time_textView;
        TextView company_name_textView;
        TextView ac_status_textView;
        TextView fare_textView;
        TextView seat_no_textView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            dept_date_textView = itemView.findViewById(R.id.ticket_history_list_recycleview_item_date);
            dept_time_textView = itemView.findViewById(R.id.ticket_history_list_recycleview_item_time);
            company_name_textView = itemView.findViewById(R.id.ticket_history_list_recycleview_item_company_name);
            fare_textView = itemView.findViewById(R.id.ticket_history_list_recycleview_item_fare);
            seat_no_textView = itemView.findViewById(R.id.ticket_history_list_recycleview_item_seat_no);
            ac_status_textView = itemView.findViewById(R.id.ticket_history_list_recycleview_item_ac_status);

        }//public Holder
    }//public class Holder
}
