package com.fbr161.buskoi.ui.purchase_ticket.view.bill_payment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.ui.purchase_ticket.view.ticket_confirmation.*;


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