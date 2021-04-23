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


import com.fbr161.buskoi.R;
import com.fbr161.buskoi.ui.purchase_ticket.backend.model.Bus;
import com.fbr161.buskoi.ui.purchase_ticket.backend.model.SeatCondition;
import com.fbr161.buskoi.ui.purchase_ticket.backend.viewmodel.ViewModel_PurchaseTicket;
import com.fbr161.buskoi.ui.purchase_ticket.view.bill_payment.BillPaymentFragment;


public class SeatSelectionFragment extends Fragment {

    Context context;
    RecyclerView recyclerView;
    Button continue_button;



    ViewModel_PurchaseTicket viewModel_PurchaseTicket;

    String schedule_id="";
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

        //Log.d("wtfffff", "oncreateView");


        recyclerView = view.findViewById(R.id.seat_selection_fragment_recycleview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(context));


        continue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragement_container, new BillPaymentFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });



        return view;
    }
    /////////////////////////


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Log.d("wtffffff_fragment_seat_selection", "onActivityCreated created");
        if(getArguments()!=null){
            //Log.d("wtffffff_fragment_seat_selection", "onActivityCreated if pass");
            int i = getArguments().getInt("index");
            //Log.d("wtffffff_fragment_seat_selection", "onActivityCreated i init: "+i);
            ////Log.d("wtffffff", schedule_id);
            viewModel_PurchaseTicket = ViewModelProviders.of(requireActivity()).get(ViewModel_PurchaseTicket.class);
            //Log.d("wtffffff_fragment_seat_selection", "onActivityCreated bus_info init");
            bus_info = viewModel_PurchaseTicket.getBus(i);
            //Log.d("wtffffff_fragment_seat_selection", "onActivityCreated bus_info init after");
            String ac_status = "";
            if(bus_info.getAc_status())
                ac_status = "A/C";
            else ac_status = "Non A/C";
            //Log.d("wtffffff_fragment_seat_selection", "dept_time_textView "+ bus_info.getDepr_time());
            dept_time_textView.setText(bus_info.getDepr_time());

            //Log.d("wtffffff_fragment_seat_selection", "company_name_textView "+ bus_info.getCompany_name());
            company_name_textView.setText(bus_info.getCompany_name());

            //Log.d("wtffffff_fragment_seat_selection", "ac_status_textView "+ ac_status);
            ac_status_textView.setText(ac_status);

            //Log.d("wtffffff_fragment_seat_selection", "fare_textView "+ bus_info.getFare());
            fare_textView.setText("৳"+bus_info.getFare());

            //Log.d("wtffffff_fragment_seat_selection", "available_seat_textView "+ bus_info.getAvailable_seat());
            available_seat_textView.setText(bus_info.getAvailable_seat()+"");


            //Log.d("wtffffff_fragment_seat_selection", "onActivityCreated viewModel_PurchaseTicket.getBus finish, schdl id: "+bus_info.getSchedule_id());

            //viewModel_PurchaseTicket.setSelected_bus_schedule_id(schedule_id);
            viewModel_PurchaseTicket.getSeatConditionLiveData(bus_info.getSchedule_id()).observe(getViewLifecycleOwner(), new Observer<SeatCondition>() {
                @Override
                public void onChanged(SeatCondition seatCondition) {

                    //Log.d("wtffffff_fragment_seat_selection", "viewModel_PurchaseTicket.getSeatConditionLiveData end: "+seatCondition.getSchedule_id());
                    recyclerView.setAdapter(new Seat_Selection_Fragment_Seat_Plan_RecycleView_Adapter(seatCondition, context));
                }
            });
        }

    }
}