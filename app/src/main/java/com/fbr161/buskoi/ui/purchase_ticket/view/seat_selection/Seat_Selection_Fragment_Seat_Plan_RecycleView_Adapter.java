package com.fbr161.buskoi.ui.purchase_ticket.view.seat_selection;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.ui.purchase_ticket.backend.model.SeatCondition;
import com.fbr161.buskoi.ui.purchase_ticket.backend.viewmodel.ViewModel_PurchaseTicket;

import java.util.ArrayList;
//import com.fbr161.buskoi.ui.purchase_ticket.view.seat_selection.Seat_Selection_Fragment_Seat_Plan_RecycleView_Adapter;

public class Seat_Selection_Fragment_Seat_Plan_RecycleView_Adapter extends RecyclerView.Adapter<Seat_Selection_Fragment_Seat_Plan_RecycleView_Adapter.Holder>{

    ViewModel_PurchaseTicket viewModel_purchaseTicket;

    SeatCondition seatCondition;
    private boolean[][] user_selected_Seat_boolArray;
    private boolean[][] bus_Seat_Condition_Array;
    private boolean[][] users_previously_booked_seats_array;
    private Context context;
    private int seat_select_count = 0;
    private final int max_seat = 4;

    private double totalFare = 0;
    private double eachTicketFare;

    public Seat_Selection_Fragment_Seat_Plan_RecycleView_Adapter(SeatCondition seatCondition, Context context, ViewModel_PurchaseTicket viewModel_purchaseTicket) {
        this.seatCondition = seatCondition;
        this.bus_Seat_Condition_Array = seatCondition.getSeat_condition();
        this.context = context;
        this.users_previously_booked_seats_array = getPreviously_booked_seats_in_boolArray(seatCondition.getUser_previous_booked_seat_record());

        user_selected_Seat_boolArray = bool2DLikeArrayInitialization(bus_Seat_Condition_Array, false);

        this.viewModel_purchaseTicket = viewModel_purchaseTicket;
        this.eachTicketFare = viewModel_purchaseTicket.getEachTicketFare();

        Log.d("wtfffff", eachTicketFare+"");
    }

    public boolean[][] getUser_selected_Seat_boolArray() {
        return user_selected_Seat_boolArray;
    }

    private boolean[][] bool2DLikeArrayInitialization(boolean[][] likeArray, boolean value){
        boolean[][] arr = new boolean[likeArray.length][likeArray[0].length];
        
        for (int i=0; i<likeArray.length; i++){
            for (int j=0; j<likeArray[0].length; j++){
                arr[i][j] = value;
            }
        }return arr;
    }

    private boolean[][] getPreviously_booked_seats_in_boolArray(String seats){

        boolean[][] arr = bool2DLikeArrayInitialization(bus_Seat_Condition_Array, false);
        String[] seat_split = seats.split(",");

        ArrayList<int[]> previously_booked_seats = new ArrayList<int[]>();

        try {
            for (String seat_no:seat_split) {
                int col = seat_no.substring(seat_no.length()-1).charAt(0) - 'A';
                if(col > 4) continue;
                int row = Integer.parseInt(seat_no.substring(0,seat_no.length()-1)) -1;
                if(row > 10) continue;
                arr[row][col] = true;
            }
        }catch (Exception e){ }
        return arr;
    }

    public String getSeatNumbersInString(boolean[][] arr){
        String seatsStr = "";
        String s = "";

        for(int i=0; i < arr.length; i++){
            for(int j=0; j<arr[0].length; j++){
                if(arr[i][j]){
                    s = (i+1)+""+(char)(65+j);  // 'A' ASCII = 65
                    if(seatsStr.equals("")){
                        seatsStr+=s;
                    }else seatsStr+=","+s;
                }
            }
        }
        if(seatsStr.equals(""))
            seatsStr=" ";

        return seatsStr;
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

        final boolean seat_status_A = bus_Seat_Condition_Array[i][0];
        final boolean seat_status_B = bus_Seat_Condition_Array[i][1];
        final boolean seat_status_C = bus_Seat_Condition_Array[i][2];
        final boolean seat_status_D = bus_Seat_Condition_Array[i][3];

        final boolean previously_booked_seat_status_A = users_previously_booked_seats_array[i][0];
        final boolean previously_booked_seat_status_B = users_previously_booked_seats_array[i][1];
        final boolean previously_booked_seat_status_C = users_previously_booked_seats_array[i][2];
        final boolean previously_booked_seat_status_D = users_previously_booked_seats_array[i][3];

        final String row = (i+1)+"";
        final int index = i;
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

        //users_previously_booked_seats
        if (previously_booked_seat_status_A)
            holder.seat_column_A.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_users_previously_booked));

        if (previously_booked_seat_status_B)
            holder.seat_column_B.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_users_previously_booked));

        if (previously_booked_seat_status_C)
            holder.seat_column_C.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_users_previously_booked));

        if (previously_booked_seat_status_D)
            holder.seat_column_D.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_users_previously_booked));



        holder.seat_column_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (seat_status_A){

                    if (!user_selected_Seat_boolArray[index][0]){             //checking seat is not selected
                        if (seat_select_count == max_seat){
                            Toast.makeText(context, "Maximum 4 seats", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        user_selected_Seat_boolArray[index][0] = true;
                        seat_select_count++;
                        hldr.seat_column_A.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_selected));

                        totalFare+=eachTicketFare;
                        viewModel_purchaseTicket.setTicketTotalFare(totalFare);     //setting total fare in viewModel
                        viewModel_purchaseTicket.setSelectedSeatNumbers(getSeatNumbersInString(user_selected_Seat_boolArray));  //update seat no in viewModel
                        viewModel_purchaseTicket.setSelectedSeatNoAndTotalFare();

                        //Toast.makeText(context, row+"A"+" selected", Toast.LENGTH_SHORT).show();
                    }else {
                        user_selected_Seat_boolArray[index][0] = false;
                        seat_select_count--;
                        hldr.seat_column_A.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_available));

                        totalFare-=eachTicketFare;
                        viewModel_purchaseTicket.setTicketTotalFare(totalFare);     //setting total fare in viewModel
                        viewModel_purchaseTicket.setSelectedSeatNumbers(getSeatNumbersInString(user_selected_Seat_boolArray));  //update seat no in viewModel
                        //Log.d("wtfffffff", (getSeatNumbersInString(user_selected_Seat_boolArray)==null)+", "+totalFare);
                        viewModel_purchaseTicket.setSelectedSeatNoAndTotalFare();

                        //Toast.makeText(context, row+"A"+" unselected", Toast.LENGTH_SHORT).show();
                    }
                }else Toast.makeText(context, row+"A"+" not available", Toast.LENGTH_SHORT).show();

            }
        });

        holder.seat_column_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (seat_status_B){
                    if (!user_selected_Seat_boolArray[index][1]){
                        if (seat_select_count == max_seat){
                            Toast.makeText(context, "Maximum 4 seats", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        user_selected_Seat_boolArray[index][1] = true;
                        seat_select_count++;
                        hldr.seat_column_B.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_selected));

                        totalFare+=eachTicketFare;
                        viewModel_purchaseTicket.setTicketTotalFare(totalFare);     //setting total fare in viewModel
                        viewModel_purchaseTicket.setSelectedSeatNumbers(getSeatNumbersInString(user_selected_Seat_boolArray));  //update seat no in viewModel
                        viewModel_purchaseTicket.setSelectedSeatNoAndTotalFare();

                        //Toast.makeText(context, row+"B"+" selected", Toast.LENGTH_SHORT).show();
                    }else {
                        user_selected_Seat_boolArray[index][1] = false;
                        seat_select_count--;
                        hldr.seat_column_B.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_available));

                        totalFare-=eachTicketFare;
                        viewModel_purchaseTicket.setTicketTotalFare(totalFare);     //setting total fare in viewModel
                        viewModel_purchaseTicket.setSelectedSeatNumbers(getSeatNumbersInString(user_selected_Seat_boolArray));  //update seat no in viewModel
                        viewModel_purchaseTicket.setSelectedSeatNoAndTotalFare();

                        //Toast.makeText(context, row+"B"+" unselected", Toast.LENGTH_SHORT).show();
                    }
                }else Toast.makeText(context, row+"B"+" not available", Toast.LENGTH_SHORT).show();

                //Log.d("wtfffff_adapter", viewModel_purchaseTicket.getSelected_seat_no());
            }
        });

        holder.seat_column_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (seat_status_C){
                    if (!user_selected_Seat_boolArray[index][2]){
                        if (seat_select_count == max_seat){
                            Toast.makeText(context, "Maximum 4 seats", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        user_selected_Seat_boolArray[index][2] = true;
                        seat_select_count++;
                        hldr.seat_column_C.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_selected));

                        totalFare+=eachTicketFare;
                        viewModel_purchaseTicket.setTicketTotalFare(totalFare);     //setting total fare in viewModel
                        viewModel_purchaseTicket.setSelectedSeatNumbers(getSeatNumbersInString(user_selected_Seat_boolArray));  //update seat no in viewModel
                        viewModel_purchaseTicket.setSelectedSeatNoAndTotalFare();

                        //Toast.makeText(context, row+"C"+" selected", Toast.LENGTH_SHORT).show();
                    }else {
                        user_selected_Seat_boolArray[index][2] = false;
                        seat_select_count--;
                        hldr.seat_column_C.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_available));

                        totalFare-=eachTicketFare;
                        viewModel_purchaseTicket.setTicketTotalFare(totalFare);     //setting total fare in viewModel
                        viewModel_purchaseTicket.setSelectedSeatNumbers(getSeatNumbersInString(user_selected_Seat_boolArray));  //update seat no in viewModel
                        viewModel_purchaseTicket.setSelectedSeatNoAndTotalFare();

                        //Toast.makeText(context, row+"C"+" unselected", Toast.LENGTH_SHORT).show();
                    }
                }else Toast.makeText(context, row+"C"+" not available", Toast.LENGTH_SHORT).show();

            }
        });

        holder.seat_column_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (seat_status_D){
                    if (!user_selected_Seat_boolArray[index][3]){
                        if (seat_select_count == max_seat){
                            Toast.makeText(context, "Maximum 4 seats", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        user_selected_Seat_boolArray[index][3] = true;
                        seat_select_count++;
                        hldr.seat_column_D.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_selected));

                        totalFare+=eachTicketFare;
                        viewModel_purchaseTicket.setTicketTotalFare(totalFare);     //setting total fare in viewModel
                        viewModel_purchaseTicket.setSelectedSeatNumbers(getSeatNumbersInString(user_selected_Seat_boolArray));  //update seat no in viewModel
                        viewModel_purchaseTicket.setSelectedSeatNoAndTotalFare();

                        //Toast.makeText(context, row+"D"+" selected", Toast.LENGTH_SHORT).show();
                    }else {
                        user_selected_Seat_boolArray[index][3] = false;
                        seat_select_count--;
                        hldr.seat_column_D.setBackground(ContextCompat.getDrawable(context, R.drawable.seat_shape_available));

                        totalFare-=eachTicketFare;
                        viewModel_purchaseTicket.setTicketTotalFare(totalFare);     //setting total fare in viewModel
                        viewModel_purchaseTicket.setSelectedSeatNumbers(getSeatNumbersInString(user_selected_Seat_boolArray));  //update seat no in viewModel
                        viewModel_purchaseTicket.setSelectedSeatNoAndTotalFare();

                        //Toast.makeText(context, row+"D"+" unselected", Toast.LENGTH_SHORT).show();
                    }
                }else Toast.makeText(context, row+"D"+" not available", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return bus_Seat_Condition_Array.length;
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
