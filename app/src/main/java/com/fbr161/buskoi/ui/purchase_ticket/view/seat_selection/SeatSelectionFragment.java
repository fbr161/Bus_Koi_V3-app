package com.fbr161.buskoi.ui.purchase_ticket.view.seat_selection;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fbr161.buskoi.MainActivity;
import com.fbr161.buskoi.R;
import com.fbr161.buskoi.ui.purchase_ticket.backend.model.Bus;
import com.fbr161.buskoi.constant.Constant;
import com.fbr161.buskoi.ui.purchase_ticket.backend.model.SeatCondition;
import com.fbr161.buskoi.ui.purchase_ticket.backend.viewmodel.ViewModel_PurchaseTicket;
import com.fbr161.buskoi.ui.purchase_ticket.view.bill_payment.BillPaymentFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;


public class SeatSelectionFragment extends Fragment {

    Context context;
    RecyclerView recyclerView;
    Button continue_button;

    

    ViewModel_PurchaseTicket viewModel_PurchaseTicket;

    String schedule_id="";
    Bus bus_info;
    String selected_seat_string="";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_seat_selection, container, false);
        context = getContext();
        continue_button = view.findViewById(R.id.seat_selection_fragment_continue_button);


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

        if(getArguments()!=null){
            schedule_id = getArguments().getString("schedule_id");
            Log.d("wtffffff", schedule_id);
            viewModel_PurchaseTicket = ViewModelProviders.of(requireActivity()).get(ViewModel_PurchaseTicket.class);
            viewModel_PurchaseTicket.setSelected_bus_schedule_id(schedule_id);
            viewModel_PurchaseTicket.getSeatConditionLiveData(schedule_id).observe(getViewLifecycleOwner(), new Observer<SeatCondition>() {
                @Override
                public void onChanged(SeatCondition seatCondition) {

                    recyclerView.setAdapter(new Seat_Selection_Fragment_Seat_Plan_RecycleView_Adapter(seatCondition, context));
                }
            });
        }

    }
}