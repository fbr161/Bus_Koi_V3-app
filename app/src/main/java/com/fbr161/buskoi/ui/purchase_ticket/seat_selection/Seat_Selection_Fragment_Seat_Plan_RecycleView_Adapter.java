package com.fbr161.buskoi.ui.purchase_ticket.seat_selection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.fbr161.buskoi.R;

public class Seat_Selection_Fragment_Seat_Plan_RecycleView_Adapter extends RecyclerView.Adapter<Seat_Selection_Fragment_Seat_Plan_RecycleView_Adapter.Holder>{

    boolean[][] bus_Seat_Plan;
    Context context;

    public Seat_Selection_Fragment_Seat_Plan_RecycleView_Adapter(boolean[][] bus_Seat_Plan, Context context) {
        this.bus_Seat_Plan = bus_Seat_Plan;
        this.context = context;
    }

    @NonNull
    @Override
    public Seat_Selection_Fragment_Seat_Plan_RecycleView_Adapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.seat_plan_recycleview_item, viewGroup, false);

        return new Seat_Selection_Fragment_Seat_Plan_RecycleView_Adapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Seat_Selection_Fragment_Seat_Plan_RecycleView_Adapter.Holder holder, int i) {

        final boolean seat_status_A = bus_Seat_Plan[i][0];
        final boolean seat_status_B = bus_Seat_Plan[i][1];
        final boolean seat_status_C = bus_Seat_Plan[i][2];
        final boolean seat_status_D = bus_Seat_Plan[i][3];
        final String row = (i+1)+"";
        final Holder hldr = holder;

        holder.row_number.setText(row);

        if (seat_status_A)
            holder.seat_column_A.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_available));
        else
            holder.seat_column_A.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_booked));

        if (seat_status_B)
            holder.seat_column_B.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_available));
        else
            holder.seat_column_B.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_booked));

        if (seat_status_C)
            holder.seat_column_C.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_available));
        else
            holder.seat_column_C.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_booked));

        if (seat_status_D)
            holder.seat_column_D.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_available));
        else
            holder.seat_column_D.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_booked));


        holder.seat_column_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (seat_status_A){
                    hldr.seat_column_A.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_selected));
                    Toast.makeText(context, row+"A"+" selected", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(context, row+"A"+" not available", Toast.LENGTH_SHORT).show();

            }
        });

        holder.seat_column_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (seat_status_B){
                    hldr.seat_column_B.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_selected));
                    Toast.makeText(context, row+"B"+" selected", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(context, row+"B"+" not available", Toast.LENGTH_SHORT).show();

            }
        });

        holder.seat_column_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (seat_status_C){
                    hldr.seat_column_C.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_selected));
                    Toast.makeText(context, row+"C"+" selected", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(context, row+"C"+" not available", Toast.LENGTH_SHORT).show();

            }
        });

        holder.seat_column_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (seat_status_D){
                    hldr.seat_column_D.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_selected));
                    Toast.makeText(context, row+"D"+" selected", Toast.LENGTH_SHORT).show();
                }else Toast.makeText(context, row+"D"+" not available", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return bus_Seat_Plan.length;
    }

    public class Holder extends RecyclerView.ViewHolder{

        LinearLayout seat_column_A;
        LinearLayout seat_column_B;
        LinearLayout seat_column_C;
        LinearLayout seat_column_D;
        TextView row_number;

        public Holder(@NonNull View itemView) {
            super(itemView);
            seat_column_A = itemView.findViewById(R.id.seat_plan_recycleview_item_column_A);
            seat_column_B = itemView.findViewById(R.id.seat_plan_recycleview_item_column_B);
            seat_column_C = itemView.findViewById(R.id.seat_plan_recycleview_item_column_C);
            seat_column_D = itemView.findViewById(R.id.seat_plan_recycleview_item_column_D);
            row_number = itemView.findViewById(R.id.seat_plan_recycleview_item_row_nmbr);

        }
    }
}