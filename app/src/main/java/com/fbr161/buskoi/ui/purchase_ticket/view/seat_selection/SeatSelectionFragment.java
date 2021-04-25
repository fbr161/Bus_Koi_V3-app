package com.fbr161.buskoi.ui.purchase_ticket.view.seat_selection;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.fbr161.buskoi.R;
import com.fbr161.buskoi.ui.purchase_ticket.backend.model.Bus;
import com.fbr161.buskoi.ui.purchase_ticket.backend.model.SeatCondition;
import com.fbr161.buskoi.ui.purchase_ticket.backend.viewmodel.ViewModel_PurchaseTicket;
import com.fbr161.buskoi.ui.purchase_ticket.view.bill_payment.BillPaymentFragment;

import java.util.HashMap;


public class SeatSelectionFragment extends Fragment {

    Context context;
    RecyclerView recyclerView;
    Button continue_button;

    ViewModel_PurchaseTicket viewModel_PurchaseTicket;

    Bus bus_info;
    String selected_seat_string="";

    TextView frm_textView;
    TextView to_textView;
    TextView date_day_textView;

    TextView dept_time_textView;
    TextView company_name_textView;
    TextView fare_textView;
    TextView available_seat_textView;
    TextView ac_status_textView;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_seat_selection, container, false);
        context = getContext();
        continue_button = view.findViewById(R.id.seat_selection_fragment_continue_button);

        frm_textView = view.findViewById(R.id.sseat_selection__fragment_from_textView);
        to_textView = view.findViewById(R.id.sseat_selection__fragment_to_textView);
        date_day_textView = view.findViewById(R.id.sseat_selection_fragment_date_day_textview);

        dept_time_textView = view.findViewById(R.id.seat_selection_bus_info__time);
        company_name_textView = view.findViewById(R.id.seat_selection_bus_info_company_name);
        fare_textView = view.findViewById(R.id.seat_selection_bus_info_fare);
        available_seat_textView = view.findViewById(R.id.seat_selection_bus_info_available_seat);
        ac_status_textView = view.findViewById(R.id.seat_selection_bus_info_ac_status);

        //ViewModel init
        viewModel_PurchaseTicket = ViewModelProviders.of(requireActivity()).get(ViewModel_PurchaseTicket.class);

        recyclerView = view.findViewById(R.id.seat_selection_fragment_recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(context));

        //Log.d("wtffffff","onCreate");
        init();
        init2();

        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("wtfffff", totalFare+"");
                if((int)totalFare==0) {
                    Toast.makeText(context, "Select at least one seat", Toast.LENGTH_SHORT).show();
                    return;
                }

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragement_container, new BillPaymentFragment());
                fragmentTransaction.addToBackStack(getString(R.string.eTicketing_fragment_backStack_name));
                fragmentTransaction.commit();
            }
        });


        return view;
    }
    /////////////////////////

    String schedule_id = "";
    boolean ac_status = false;
    String depr_time = "";
    String company_name = "";

    double eachTicketfare = 0;
    double totalFare = 0;

    int available_seat = 0;
    String selected_seat_no = "";


    public void init() {

//        if(getArguments()!=null){
//            int i = getArguments().getInt("index");

//            bus_info = viewModel_PurchaseTicket.getBus(i);
//
//            schedule_id = bus_info.getSchedule_id();
//            depr_time = bus_info.getDepr_time();
//            company_name = bus_info.getCompany_name();
//            ac_status = bus_info.getAc_status();
//            eachTicketfare = bus_info.getFare();
//            available_seat = bus_info.getAvailable_seat();
//
//            dept_time_textView.setText(depr_time);
//            company_name_textView.setText(company_name);
//            ac_status_textView.setText(acStatusStr);

            fare_textView.setText("৳ 0");
            available_seat_textView.setText("No seat selected");

            viewModel_PurchaseTicket.getDepTime_CompanyName_AcStatus_Fare_SeatNo().observe(getViewLifecycleOwner(), new Observer<HashMap<String, String>>() {
                @Override
                public void onChanged(HashMap<String, String> stringStringHashMap) {
                    depr_time = stringStringHashMap.get("dep_time");
                    company_name = stringStringHashMap.get("company_name");
                    ac_status = Boolean.parseBoolean(stringStringHashMap.get("ac_status"));
                    selected_seat_no = stringStringHashMap.get("seat_no");
                    totalFare = Double.parseDouble(stringStringHashMap.get("totalFare"));

                    String acStatusStr = "";
                    if(ac_status)
                        acStatusStr = "A/C";
                    else acStatusStr = "Non A/C";

                    dept_time_textView.setText(depr_time);
                    company_name_textView.setText(company_name);
                    ac_status_textView.setText(acStatusStr);

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

//        }

    }


    public void init2() {

        viewModel_PurchaseTicket.getSeatConditionLiveData(viewModel_PurchaseTicket.getIssueTicketMutable().getSchedule_id()).observe(getViewLifecycleOwner(), new Observer<SeatCondition>() {
            @Override
            public void onChanged(SeatCondition seatCondition) {

                recyclerView.setAdapter(new Seat_Selection_Fragment_Seat_Plan_RecycleView_Adapter(seatCondition, context, viewModel_PurchaseTicket));
            }
        });

        viewModel_PurchaseTicket.getSelectedSeatNoAndTotalFare().observe(getViewLifecycleOwner(), new Observer<HashMap<String, String>>() {

            @Override
            public void onChanged(HashMap<String, String> stringStringHashMap) {

                selected_seat_no = stringStringHashMap.get("selected_seat_no");
                totalFare = Double.parseDouble(stringStringHashMap.get("totalFare"));

                if(selected_seat_no.equals(" ")){
                    available_seat_textView.setText("No seat selected");
                    fare_textView.setText("৳ 0");
                }else {
                    available_seat_textView.setText(selected_seat_no);
                    fare_textView.setText("৳ " + totalFare);
                }
            }
        });

    }
}