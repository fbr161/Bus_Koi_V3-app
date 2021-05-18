package com.fbr161.buskoi.ui.emergency_contacts.view.contact_editText;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.constant.Constant;
import com.fbr161.buskoi.ui.emergency_contacts.backend.viewModel.ViewModel_Emergency_Contact;
import com.fbr161.buskoi.ui.emergency_contacts.view.contact_list.Emergency_Contact_List_Fragment;

import static android.content.Context.MODE_PRIVATE;

public class Emergency_Contact_EditText_Fragment extends Fragment {

    String user_phn_no = "";
    String emergency_contact_name = "";
    String emergency_contact_phn_no = "";

    EditText name_EditText;
    EditText phn_no_EditText;
    Button action_Button;

    ViewModel_Emergency_Contact viewModel_emergency_contact;

    Bundle arg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_emergency_contact_edit_text, container, false);
        viewModel_emergency_contact = ViewModelProviders.of(requireActivity()).get(ViewModel_Emergency_Contact.class);

        name_EditText = view.findViewById(R.id.emergency_contact_edit_text_fragment_name_editText);
        phn_no_EditText = view.findViewById(R.id.emergency_contact_edit_text_fragment_phn_no_editText);
        action_Button = view.findViewById(R.id.emergency_contact_edit_text_fragment_update_button);

        SharedPreferences user_info_pref = getContext().getApplicationContext().getSharedPreferences(Constant.SharedPreferences.USER_INFO_SHARED_PREF, MODE_PRIVATE); // 0 - for private mode
        user_phn_no = user_info_pref.getString(Constant.SharedPreferences.KEY___USER_PHONE_NO, "");

        if(getArguments() != null) {

            arg = getArguments();
            //Log.d("wtfffffff_edit_contact", arg.getString(Constant.Emergency_Contact.EMERGENCY_CONTACT_EDIT_TEXT_ACTION_STATUS_KEY));

            // if contact upate action
            if (arg.getString(Constant.Emergency_Contact.EMERGENCY_CONTACT_EDIT_TEXT_ACTION_STATUS_KEY).equals(Constant.Emergency_Contact.EMERGENCY_CONTACT_EDIT_TEXT_UPDATE_ACTION)) {
                emergency_contact_name = arg.getString(Constant.Emergency_Contact.EMERGENCY_CONTACT_NAME_ARG_KEY, "");
                emergency_contact_phn_no = arg.getString(Constant.Emergency_Contact.EMERGENCY_CONTACT_PHONE_NO_ARG_KEY, "");

                if (emergency_contact_name.equals("") || emergency_contact_phn_no.equals("")) {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                            new Emergency_Contact_List_Fragment()).commit();
                }

                name_EditText.setText(emergency_contact_name);
                phn_no_EditText.setText(emergency_contact_phn_no);

            }

            action_Button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    buttonAction();
                }
            });

        }
        return view;
    }

    private void buttonAction(){

        ///////////////////////// Insert contact
        if(arg.getString(Constant.Emergency_Contact.EMERGENCY_CONTACT_EDIT_TEXT_ACTION_STATUS_KEY).equals(Constant.Emergency_Contact.EMERGENCY_CONTACT_EDIT_TEXT_ADD_ACTION)){


            //Log.d("wtfffffff_edit_contact_insert", ""+arg.getString(Constant.Emergency_Contact.EMERGENCY_CONTACT_EDIT_TEXT_ADD_ACTION));

            emergency_contact_name = name_EditText.getText().toString();
            emergency_contact_phn_no = phn_no_EditText.getText().toString();

            if(emergency_contact_name.equals("")) {
                Toast.makeText(getContext(), "Enter name", Toast.LENGTH_SHORT).show();
            }else if(emergency_contact_phn_no.equals("")) {
                Toast.makeText(getContext(), "Enter phone no", Toast.LENGTH_SHORT).show();
            }else{
                viewModel_emergency_contact.insertEmergencyContact(user_phn_no, emergency_contact_phn_no, emergency_contact_name).observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if(aBoolean) {
                            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                                    new Emergency_Contact_List_Fragment()).commit();

                        }else{
                            Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

        }

        ///////////////////////// Update contact
        else if(arg.getString(Constant.Emergency_Contact.EMERGENCY_CONTACT_EDIT_TEXT_ACTION_STATUS_KEY).equals(Constant.Emergency_Contact.EMERGENCY_CONTACT_EDIT_TEXT_UPDATE_ACTION)){

            emergency_contact_name = name_EditText.getText().toString();
            emergency_contact_phn_no = phn_no_EditText.getText().toString();

            if(emergency_contact_name.equals("")) {
                Toast.makeText(getContext(), "Enter name", Toast.LENGTH_SHORT).show();
            }else if(emergency_contact_phn_no.equals("")) {
                Toast.makeText(getContext(), "Enter phone no", Toast.LENGTH_SHORT).show();
            }else{
                viewModel_emergency_contact.updateEmergencyContact(user_phn_no, emergency_contact_phn_no, emergency_contact_name).observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if(aBoolean) {
                            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container,
                                    new Emergency_Contact_List_Fragment()).commit();

                        }else{
                            Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }

    }
}