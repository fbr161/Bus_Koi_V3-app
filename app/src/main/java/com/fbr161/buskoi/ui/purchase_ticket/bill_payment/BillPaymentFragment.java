package com.fbr161.buskoi.ui.purchase_ticket.bill_payment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.clss.Bus_Seat_Plan;
import com.fbr161.buskoi.ui.purchase_ticket.seat_selection.Seat_Selection_Fragment_Seat_Plan_RecycleView_Adapter;
import com.fbr161.buskoi.ui.purchase_ticket.ticket_confirmation.TicketConfirmationFragment;


public class BillPaymentFragment extends Fragment {

    Context context;
    Button pay_bill_button;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bill_payment, container, false);
        context = getContext();

        pay_bill_button = view.findViewById(R.id.bill_payment_fragment_pay_bill_button);

        pay_bill_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragement_container, new TicketConfirmationFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}