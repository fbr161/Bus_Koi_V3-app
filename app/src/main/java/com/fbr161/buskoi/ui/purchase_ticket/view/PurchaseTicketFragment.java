package com.fbr161.buskoi.ui.purchase_ticket.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.constant.Constant;
import com.fbr161.buskoi.ui.purchase_ticket.backend.viewmodel.ViewModel_PurchaseTicket;
import com.fbr161.buskoi.ui.purchase_ticket.view.location_picker.*;
import com.fbr161.buskoi.ui.purchase_ticket.view.searched_bus_list.SearchedBusListFragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class PurchaseTicketFragment extends Fragment {

    ViewModel_PurchaseTicket viewModel_PurchaseTicket;

    DatePickerDialog datePickerDialog;
    Button search_button;
    Context context;
    LinearLayout from_location_layout, to_location_layout, date_picker_layout;
    TextView show_date_textView;
    TextView from_location_textView;
    TextView to_location_textView;
//    Toolbar toolbar;

    ////////
    String from_location="";
    String to_location = "";
    String date = "";

    String dayOfWeek;

    public PurchaseTicketFragment(String from_location,  String to_location, String date){
        this.from_location = from_location;
        this.to_location = to_location;
        this.date = date;
    }


    private void assign_values(){
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        date = formatter.format(new Date());

        try {
            Date dt = formatter.parse(date);
            dayOfWeek =  new SimpleDateFormat("EEEE").format(dt);
        }catch (ParseException e){
            e.printStackTrace();
        }

        //date
        if(date.equals(""))
            show_date_textView.setText(date);
        else  show_date_textView.setText(this.date);

        //from
        if(!from_location.equals(""))
            from_location_textView.setText(from_location);

        //to
        if(!to_location.equals(""))
            to_location_textView.setText(to_location);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_purchase_ticket, container, false);
        context=getContext();

        from_location_layout = view.findViewById(R.id.purchase_ticket_from);
        to_location_layout = view.findViewById(R.id.purchase_ticket_to);
        date_picker_layout = view.findViewById(R.id.purchase_ticket_fragment_date_picker_layout);

        from_location_textView = view.findViewById(R.id.purchase_ticket_from_location_textview);
        to_location_textView = view.findViewById(R.id.purchase_ticket_to_location_textview);
        show_date_textView = view.findViewById(R.id.purchase_ticket_date_text_view);

        search_button = view.findViewById(R.id.purchase_ticket_search_button);

//        toolbar = view.findViewById(R.id.toolbar);
//        toolbar.setTitle("Bus Koi");

        assign_values();

        from_location_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragement_container, new LocationPickerFragment(from_location,to_location,date, Constant.LOCATION_PICKER_FROM));
                fragmentTransaction.commit();

                //Toast.makeText(context, "from location", Toast.LENGTH_SHORT).show();
            }
        });

        to_location_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragement_container, new LocationPickerFragment(from_location,to_location,date, Constant.LOCATION_PICKER_TO));
                fragmentTransaction.commit();

                //Toast.makeText(context, "to location", Toast.LENGTH_SHORT).show();
            }
        });

        date_picker_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day_ = cldr.get(Calendar.DAY_OF_MONTH);
                int month_ = cldr.get(Calendar.MONTH);
                int year_ = cldr.get(Calendar.YEAR);
                // date picker dialog
                datePickerDialog = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int _year, int monthOfYear, int dayOfMonth) {

                                ////
                                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                                try {
                                    Date picked_date = formatter.parse(dayOfMonth+"-"+(monthOfYear+1)+"-"+_year);
                                    Date presentDate = formatter.parse(formatter.format(new Date()));

                                    if(picked_date.compareTo(presentDate)<0){
                                        Toast.makeText(context, "You can't select an old date for your Trip", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    date = formatter.format(picked_date);
                                    dayOfWeek =  new SimpleDateFormat("EEEE").format(picked_date);
                                    show_date_textView.setText(date);
                                }catch (ParseException e){
                                    e.printStackTrace();
                                }

                                ////

                                //Toast.makeText(context, dayOfWeek, Toast.LENGTH_SHORT).show();

                            }
                        }, year_, month_, day_);
                datePickerDialog.show();

                //Toast.makeText(context, day + "/" + (month + 0) + "/" + year, Toast.LENGTH_SHORT).show();

            }
        });

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(from_location_textView.getText().toString().equals(getString(R.string.from_location_name_textBox))) {
                    //Log.d("wtfffff", "in if");
                    Toast.makeText(context, "You must select the city from you want to travel", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (to_location_textView.getText().toString().equals(getString(R.string.to_location_name_textBox))) {
                    Toast.makeText(context, "You must select the destination city you want to travel", Toast.LENGTH_SHORT).show();
                    return;
                }


                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragement_container, new SearchedBusListFragment());
                fragmentTransaction.addToBackStack(getString(R.string.eTicketing_fragment_backStack_name));

                //Log.d("wtffffff_fragment_PurchaseTicket", "search_button.setOnClickListener before viewModel_PurchaseTicket.setFromToDateDayName setvalue");
                viewModel_PurchaseTicket.setFromToDateDayName(from_location, to_location, date, dayOfWeek);
                //Log.d("wtffffff_fragment_PurchaseTicket", "search_button.setOnClickListener after viewModel_PurchaseTicket.setFromToDateDayName setvalue");

                fragmentTransaction.commit();

            }

        });

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Log.d("wtffffff_fragment_PurchaseTicket", "onActivityCreated before viewModel_PurchaseTicket init");
        viewModel_PurchaseTicket = ViewModelProviders.of(requireActivity()).get(ViewModel_PurchaseTicket.class);
        //Log.d("wtffffff_fragment_PurchaseTicket", "onActivityCreated after viewModel_PurchaseTicket init");
    }
}