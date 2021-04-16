package com.fbr161.buskoi.ui.purchase_ticket;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.ui.purchase_ticket.searched_bus_list.SearchedBusListFragment;

import java.util.Calendar;

public class PurchaseTicketFragment extends Fragment {

    DatePickerDialog picker;
    Button search_button;
    Context context;
    LinearLayout from_location, to_location, date_picker;
    TextView show_date_textView;
//    Toolbar toolbar;

    int day=0;
    int month=0;
    int year=0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_purchase_ticket, container, false);
        context=getContext();

        from_location = view.findViewById(R.id.purchase_ticket_from);
        to_location = view.findViewById(R.id.purchase_ticket_to);
        date_picker = view.findViewById(R.id.purchase_ticket_date_picker);
        search_button = view.findViewById(R.id.purchase_ticket_search_button);
        show_date_textView = view.findViewById(R.id.purchase_ticket_date_text_view);

//        toolbar = view.findViewById(R.id.toolbar);
//        toolbar.setTitle("Bus Koi");

//        from_location.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.fragement_container, new Search_location_Fragment());
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//
//                Toast.makeText(context, "from location", Toast.LENGTH_SHORT).show();
//            }
//        });

//        to_location.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.fragement_container, new Search_location_Fragment());
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//
//                Toast.makeText(context, "to location", Toast.LENGTH_SHORT).show();
//            }
//        });

//        date_picker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar cldr = Calendar.getInstance();
//                int day_ = cldr.get(Calendar.DAY_OF_MONTH);
//                int month_ = cldr.get(Calendar.MONTH);
//                int year_ = cldr.get(Calendar.YEAR);
//                // date picker dialog
//                picker = new DatePickerDialog(context,
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int _year, int monthOfYear, int dayOfMonth) {
//
//                                day=dayOfMonth; month=monthOfYear; year=_year;
//                                show_date_textView.setText(day + "/" + (month + 1) + "/" + year);
//
//                            }
//                        }, year_, month_, day_);
//                picker.show();
//
//                //  Toast.makeText(context, day + "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT).show();
//
//            }
//        });

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragement_container, new SearchedBusListFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        return view;
    }

}