package com.fbr161.buskoi.ui.purchase_ticket.location_picker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.fbr161.buskoi.R;
import com.fbr161.buskoi.ui.purchase_ticket.PurchaseTicketFragment;



public class Location_Picker_Fragment_Recycleview_Adapter extends RecyclerView.Adapter<Location_Picker_Fragment_Recycleview_Adapter.Holder> {

    String[] city_name;
    Context context;

    public Location_Picker_Fragment_Recycleview_Adapter(String[] city_name, Context context) {
        this.city_name = city_name;
        this.context = context;
    }

    @NonNull
    @Override
    public Location_Picker_Fragment_Recycleview_Adapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.location_picker_recyclerview_item, viewGroup, false);

        return new Location_Picker_Fragment_Recycleview_Adapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {

        final String city = city_name[i];


        holder.city_name_textview.setText(city);

        holder.city_name_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, fare+"", Toast.LENGTH_SHORT).show();
                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container, new PurchaseTicketFragment()).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return city_name.length;
    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView city_name_textview;

        public Holder(@NonNull View itemView) {
            super(itemView);
            city_name_textview = itemView.findViewById(R.id.location_picker_recyclerview_item_location_textview);
        }
    }
}
