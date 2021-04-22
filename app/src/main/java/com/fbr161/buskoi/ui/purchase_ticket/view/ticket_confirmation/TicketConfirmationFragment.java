package com.fbr161.buskoi.ui.purchase_ticket.view.ticket_confirmation;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.ui.home.HomeFragment;


public class TicketConfirmationFragment extends Fragment {

    Context context;
    LinearLayout go_to_home_button;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ticket_confirmation, container, false);
        context = getContext();

        go_to_home_button = view.findViewById(R.id.ticket_confirmation_fragment_to_home_button);

        go_to_home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragement_container, new HomeFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}