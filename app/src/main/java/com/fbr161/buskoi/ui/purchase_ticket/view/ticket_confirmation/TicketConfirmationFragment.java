package com.fbr161.buskoi.ui.purchase_ticket.view.ticket_confirmation;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.ui.home.view.HomeFragment;
import com.fbr161.buskoi.ui.purchase_ticket.backend.viewmodel.ViewModel_PurchaseTicket;

import java.util.HashMap;


public class TicketConfirmationFragment extends Fragment {

    Context context;
    ViewModel_PurchaseTicket viewModel_PurchaseTicket;

    TextView frm_textView;
    TextView to_textView;
    TextView date_day_textView;

    TextView dept_time_textView;
    TextView company_name_textView;
    TextView ac_status_textView;
    TextView total_fare_textView;
    TextView selected_seat_textView;

    TextView passenger_name_TextView;
    TextView passenger_phnNo_TextView;

    TextView purchase_status_TextView;

    LinearLayout go_to_home_button;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ticket_confirmation, container, false);
        context = getContext();
        viewModel_PurchaseTicket = ViewModelProviders.of(requireActivity()).get(ViewModel_PurchaseTicket.class);

        frm_textView = view.findViewById(R.id.ticket_confirmation_fragment_from_textView);
        to_textView = view.findViewById(R.id.ticket_confirmation_fragment_to_textView);
        date_day_textView = view.findViewById(R.id.ticket_confirmation_fragment_date_day_textview);

        dept_time_textView = view.findViewById(R.id.ticket_confirmation_ticket_info_time_textView);
        company_name_textView = view.findViewById(R.id.ticket_confirmation_ticket_info_company_name_textView);
        total_fare_textView = view.findViewById(R.id.ticket_confirmation_ticket_info_total_fare_textView);
        selected_seat_textView = view.findViewById(R.id.ticket_confirmation_ticket_info_selected_seat_number_textView);
        ac_status_textView = view.findViewById(R.id.ticket_confirmation_ticket_info_ac_status_textView);

        passenger_name_TextView = view.findViewById(R.id.ticket_confirmation_fragment_passenger_name_textView);
        passenger_phnNo_TextView = view.findViewById(R.id.ticket_confirmation_fragment_passenger_phn_no_textView);

        purchase_status_TextView = view.findViewById(R.id.ticket_confirmation_fragment_purchase_status_textview);

        go_to_home_button = view.findViewById(R.id.ticket_confirmation_fragment_to_home_button);

        //////////
        init();

        ///////////


        go_to_home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_for_home_confirmation_pop_up(context, "Leave", "Yes", "No");
            }
        });

        return view;
    }

    public void go_for_home_confirmation_pop_up(Context context, String title, String leftButtonText, String rightButtonText){

        AlertDialog.Builder mBldr=new AlertDialog.Builder(context);
        View mView = getLayoutInflater().inflate(R.layout.title_and_two_buttons_pop_up,null);

        TextView left_bttn = (TextView) mView.findViewById(R.id.left_textVw);
        TextView right_bttn = (TextView) mView.findViewById(R.id.right_textVw);
        TextView title_textView = mView.findViewById(R.id.title_textView);

        left_bttn.setText(leftButtonText);
        right_bttn.setText(rightButtonText);
        title_textView.setText(title);

        mBldr.setView(mView);
        final AlertDialog dialog= mBldr.create();
        dialog.show();



        left_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragement_container, new HomeFragment());
                fragmentTransaction.commit();

                dialog.dismiss();
            }
        });

        right_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    public void init() {

        viewModel_PurchaseTicket.getDepTime_CompanyName_AcStatus_Fare_SeatNo().observe(getViewLifecycleOwner(), new Observer<HashMap<String, String>>() {
            @Override
            public void onChanged(HashMap<String, String> stringStringHashMap) {
                String depr_time = stringStringHashMap.get("dep_time");
                String company_name = stringStringHashMap.get("company_name");
                boolean ac_status = Boolean.parseBoolean(stringStringHashMap.get("ac_status"));
                String selected_seat_no = stringStringHashMap.get("seat_no");
                double totalFare = Double.parseDouble(stringStringHashMap.get("totalFare"));

                String acStatusStr = "";
                if(ac_status)
                    acStatusStr = "A/C";
                else acStatusStr = "Non A/C";

                dept_time_textView.setText(depr_time);
                company_name_textView.setText(company_name);
                ac_status_textView.setText(acStatusStr);
                total_fare_textView.setText("৳ "+totalFare);
                selected_seat_textView.setText(selected_seat_no);

            }
        });


        viewModel_PurchaseTicket.getFromToDateDayName().observe(getViewLifecycleOwner(), new Observer<HashMap<String, String>>() {

            @Override
            public void onChanged(HashMap<String, String> stringStringHashMap) {

                //Log.d("wtfffffff", stringStringHashMap.get("from"));
                frm_textView.setText(stringStringHashMap.get("from"));
                to_textView.setText(stringStringHashMap.get("to"));
                date_day_textView.setText(stringStringHashMap.get("date")+"  |  "+stringStringHashMap.get("dayOfWeek"));

            }
        });

        viewModel_PurchaseTicket.getUserPhnNo_Name_PhnNo().observe(getViewLifecycleOwner(), new Observer<HashMap<String, String>>() {
            @Override
            public void onChanged(HashMap<String, String> stringStringHashMap) {

                passenger_name_TextView.setText(stringStringHashMap.get("name"));
                passenger_phnNo_TextView.setText(stringStringHashMap.get("phn_no"));
            }
        });

        if (false){//(getArguments() != null){
            if (!getArguments().getBoolean("result")) {
                purchase_status_TextView.setTextColor(Color.parseColor("#cc0000"));
                purchase_status_TextView.setText("Failed");
            }
        }

    }

}