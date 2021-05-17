package com.fbr161.buskoi.ui.emergency_contacts.view.contact_list;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.constant.Constant;
import com.fbr161.buskoi.ui.emergency_contacts.backend.model.UsersEmergencyContact;
import com.fbr161.buskoi.ui.emergency_contacts.backend.viewModel.ViewModel_Emergency_Contact;
import com.fbr161.buskoi.ui.emergency_contacts.view.contact_editText.Emergency_Contact_EditText_Fragment;
import com.fbr161.buskoi.ui.home.backed.viewmodel.ViewModel_BookedBus_Home;
import com.fbr161.buskoi.ui.home.view.HomeFragment;
import com.fbr161.buskoi.ui.purchase_ticket.view.searched_bus_list.Search_bus_list_Fragment_recycleView_adapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class Emergency_Contact_List_Fragment extends Fragment {

    Context context;
    RecyclerView recyclerView;

    FloatingActionButton add_button;

    ViewModel_Emergency_Contact viewModel_emergency_contact;

    String user_name;
    String user_phn_no;

    ArrayList<UsersEmergencyContact> usersEmergencyContact_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_emergency_contact_list, container, false);
        context = getContext();
        viewModel_emergency_contact = ViewModelProviders.of(requireActivity()).get(ViewModel_Emergency_Contact.class);
        usersEmergencyContact_list = new ArrayList<>();

        recyclerView = view.findViewById(R.id.emergency_contact_list_fragment_recyclerView);
        add_button = view.findViewById(R.id.emergency_contact_list_fragment_add_button);

//        ArrayList<UsersEmergencyContact> usersEmergencyContacts = new ArrayList<>();
//        usersEmergencyContacts.add(new UsersEmergencyContact("fuad", "01739"));
//        usersEmergencyContacts.add(new UsersEmergencyContact("bin", "01770"));
//        usersEmergencyContacts.add(new UsersEmergencyContact("rahman", "01730"));



        SharedPreferences user_info_pref = getContext().getApplicationContext().getSharedPreferences(Constant.SharedPreferences.USER_INFO_SHARED_PREF, MODE_PRIVATE); // 0 - for private mode
        user_name = user_info_pref.getString(Constant.SharedPreferences.KEY___USER_NAME, "");
        user_phn_no = user_info_pref.getString(Constant.SharedPreferences.KEY___USER_PHONE_NO, "");

        viewModel_emergency_contact.readEmergencyContact(user_phn_no).observe(getViewLifecycleOwner(), new Observer<UsersEmergencyContact>() {
            @Override
            public void onChanged(UsersEmergencyContact usersEmergencyContact) {

                if(usersEmergencyContact != null){
                    usersEmergencyContact_list.add(usersEmergencyContact);

                    recyclerView.setAdapter(new Emergency_Contact_List_RecyclerView_Adapter(usersEmergencyContact_list, context));
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager( new LinearLayoutManager(context));
                }
            }
        });


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usersEmergencyContact_list.size() == 0){
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container, new Emergency_Contact_EditText_Fragment()).addToBackStack(null).commit();
                }
            }
        });

        return view;
    }
}