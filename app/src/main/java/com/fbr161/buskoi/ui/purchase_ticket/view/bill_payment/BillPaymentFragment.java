package com.fbr161.buskoi.ui.purchase_ticket.view.bill_payment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.fbr161.buskoi.MainActivity;
import com.fbr161.buskoi.R;
import com.fbr161.buskoi.constant.Constant;
import com.fbr161.buskoi.ui.purchase_ticket.backend.viewmodel.ViewModel_PurchaseTicket;
import com.fbr161.buskoi.ui.purchase_ticket.view.seat_selection.SeatSelectionFragment;
import com.fbr161.buskoi.ui.purchase_ticket.view.ticket_confirmation.*;

import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;
import static androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE;


public class BillPaymentFragment extends Fragment {

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
    Button pay_bill_button;

    EditText passenger_name_editText;
    EditText passenger_phnNo_editText;

    RadioGroup genderRadioGroup;
    RadioButton selected_gender_RadioButton;

    String depr_time = "";
    String company_name = "";
    boolean ac_status = false;
    double totalFare = 0;
    String selected_seat_no = "";

    String user_phn_no = "";
    String user_name = "";
    boolean user_gender = true;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bill_payment, container, false);
        context = getContext();
        viewModel_PurchaseTicket = ViewModelProviders.of(requireActivity()).get(ViewModel_PurchaseTicket.class);

        frm_textView = view.findViewById(R.id.bill_payment_fragment_from_textView);
        to_textView = view.findViewById(R.id.bill_payment_fragment_to_textView);
        date_day_textView = view.findViewById(R.id.bill_payment_fragment_date_day_textview);

        dept_time_textView = view.findViewById(R.id.bill_payment_bus_info__time_textView);
        company_name_textView = view.findViewById(R.id.bill_payment_bus_info_company_name_textView);
        total_fare_textView = view.findViewById(R.id.bill_payment_bus_info_total_fare_textView);
        selected_seat_textView = view.findViewById(R.id.bill_payment_bus_info_selected_seat_number_textView);
        ac_status_textView = view.findViewById(R.id.bill_payment_bus_info_ac_status_textView);

        passenger_name_editText = view.findViewById(R.id.bill_payment_passenger_name_editText);
        passenger_phnNo_editText = view.findViewById(R.id.bill_payment_passenger_phn_no_editText);
        genderRadioGroup = view.findViewById(R.id.bill_payment_passenger_gender_radioGroup);

        pay_bill_button = view.findViewById(R.id.bill_payment_fragment_pay_bill_button);

        /////////////////////////Shared preference
        SharedPreferences user_info_pref = context.getSharedPreferences(Constant.SharedPreferences.USER_INFO_SHARED_PREF, MODE_PRIVATE);
        user_phn_no = user_info_pref.getString(Constant.SharedPreferences.KEY___USER_PHONE_NO, "000000");
        user_name = user_info_pref.getString(Constant.SharedPreferences.KEY___USER_NAME, "");
        user_gender = user_info_pref.getBoolean(Constant.SharedPreferences.KEY___USER_GENDER, true);


        passenger_name_editText.setText(user_name);
        passenger_phnNo_editText.setText(user_phn_no);
        if(!user_gender)
            genderRadioGroup.check(R.id.bill_payment_passenger_female_radio_button);

        init();

        final View v = view;
        pay_bill_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selected_gender_RadioButton = v.findViewById(genderRadioGroup.getCheckedRadioButtonId());
                String gender = selected_gender_RadioButton.getText().toString();
                String passengerName = passenger_name_editText.getText().toString();
                String passengerPhnNo = passenger_phnNo_editText.getText().toString();

                if(passengerName.equals("")){
                    Toast.makeText(context, "Enter passenger's name.", Toast.LENGTH_SHORT).show();
                    return;
                }else if(passengerPhnNo.equals("")){
                    Toast.makeText(context, "Enter passenger's phone no.", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean passenger_gender = true;
                if(gender.equals("Female")) passenger_gender = false;

                viewModel_PurchaseTicket.setUserPhnNo_PassengerName_PassengerPhnNo(user_phn_no, passengerName, passengerPhnNo, passenger_gender);

                pay_confirmation_pop_up(context, "Are you sure?", "Yes", "No");
            }
        });

        return view;
    }
    //confirmation popUp
    public void pay_confirmation_pop_up(Context context, String title, String leftButtonText, String rightButtonText){

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

                HashMap<String,String> hashMap =  viewModel_PurchaseTicket.getIssueTicketMutable().getPurchaseTicketParameters();

                boolean b;

                viewModel_PurchaseTicket.purchaseTicket(hashMap.get("schedule_id"), hashMap.get("passenger_phn"), hashMap.get("passenger_name"),
                                                            Boolean.parseBoolean(hashMap.get("gender")), hashMap.get("seat_no"), Double.parseDouble(hashMap.get("fare")),
                                                            hashMap.get("issued_by"));


                getFragmentManager().popBackStack(getString(R.string.eTicketing_fragment_backStack_name), POP_BACK_STACK_INCLUSIVE);
                TicketConfirmationFragment ticketConfirmationFragment = new TicketConfirmationFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragement_container, ticketConfirmationFragment);
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

    }

}