package com.fbr161.buskoi.ui.purchase_ticket.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.constant.Constant;
import com.fbr161.buskoi.ui.purchase_ticket.view.location_picker.*;
import com.fbr161.buskoi.ui.purchase_ticket.view.searched_bus_list.SearchedBusListFragment;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class PurchaseTicketFragment extends Fragment {

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

    int day=0;
    int month=0;
    int year=0;
    String dayOfWeek;

    public PurchaseTicketFragment(String from_location,  String to_location, String date){
        this.from_location = from_location;
        this.to_location = to_location;
        this.date = date;
    }


    private void assign_values(){
        date = java.time.LocalDate.now()+"";
        String[] date_split = date.split("-");
        day = Integer.parseInt(date_split[2]);
        month = Integer.parseInt(date_split[1]);
        year = Integer.parseInt(date_split[0]);

        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
        Date dt = new Date(year, month, day-3);
        dayOfWeek = simpledateformat.format(dt);

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
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                //Toast.makeText(context, "from location", Toast.LENGTH_SHORT).show();
            }
        });

        to_location_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragement_container, new LocationPickerFragment(from_location,to_location,date, Constant.LOCATION_PICKER_TO));
                fragmentTransaction.addToBackStack(null);
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

                                day=dayOfMonth; month=monthOfYear+1; year=_year;
                                date = year + "-" + (month + 0) + "-" + day;
                                show_date_textView.setText(date);

                                SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                                Date datee = new Date(year, month, day-3);
                                dayOfWeek = simpledateformat.format(datee);
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

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragement_container, new SearchedBusListFragment(from_location,to_location,date, dayOfWeek));
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        return view;
    }

}