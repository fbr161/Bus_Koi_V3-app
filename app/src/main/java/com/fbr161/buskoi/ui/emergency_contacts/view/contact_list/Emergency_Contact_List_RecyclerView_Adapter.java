package com.fbr161.buskoi.ui.emergency_contacts.view.contact_list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.constant.Constant;
import com.fbr161.buskoi.ui.emergency_contacts.backend.model.UsersEmergencyContact;
import com.fbr161.buskoi.ui.emergency_contacts.backend.viewModel.ViewModel_Emergency_Contact;
import com.fbr161.buskoi.ui.emergency_contacts.view.contact_editText.Emergency_Contact_EditText_Fragment;

import java.util.ArrayList;

public class Emergency_Contact_List_RecyclerView_Adapter extends RecyclerView.Adapter<Emergency_Contact_List_RecyclerView_Adapter.Holder>{

    ArrayList<UsersEmergencyContact> emergencyNumbers = new ArrayList<>();
    ViewModel_Emergency_Contact viewModel_emergency_contact;
    Context context;

    public Emergency_Contact_List_RecyclerView_Adapter(ArrayList<UsersEmergencyContact> emergencyNumbers, ViewModel_Emergency_Contact viewModel, Context context) {
        this.emergencyNumbers = emergencyNumbers;
        viewModel_emergency_contact = viewModel;
        this.context = context;

    }

    @NonNull
    @Override
    public Emergency_Contact_List_RecyclerView_Adapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.recycler_item_emergency_contact_list_fragment, viewGroup, false);

        return new Emergency_Contact_List_RecyclerView_Adapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Emergency_Contact_List_RecyclerView_Adapter.Holder holder, int i) {

        final String name = emergencyNumbers.get(i).getName();
        final String phn_no = emergencyNumbers.get(i).getPhn_no();

        holder.name_textView.setText(name);
        holder.phn_no_textView.setText(phn_no);


    }

    @Override
    public int getItemCount() {
        return emergencyNumbers.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        TextView name_textView;
        TextView phn_no_textView;
        ImageButton imageButton;

        public Holder(@NonNull View itemView) {
            super(itemView);
            name_textView = itemView.findViewById(R.id.emergency_contact_list_recycleview_item_name_textView);
            phn_no_textView = itemView.findViewById(R.id.emergency_contact_list_recycleview_item_phn_no_textView);
            imageButton = itemView.findViewById(R.id.emergency_contact_list_recycleview_item_more_button);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(context, view);
                    popupMenu.inflate(R.menu.emergency_contact_list_recyclerview_item_menu);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            switch (item.getItemId()){
                                case R.id.menu_emergency_contact_list_item_edit_menu:
                                    int i = getBindingAdapterPosition();

                                    Bundle arg = new Bundle();
                                    arg.putString(Constant.Emergency_Contact.EMERGENCY_CONTACT_EDIT_TEXT_ACTION_STATUS_KEY, Constant.Emergency_Contact.EMERGENCY_CONTACT_EDIT_TEXT_UPDATE_ACTION);
                                    arg.putString(Constant.Emergency_Contact.EMERGENCY_CONTACT_NAME_ARG_KEY, emergencyNumbers.get(i).getName());
                                    arg.putString(Constant.Emergency_Contact.EMERGENCY_CONTACT_PHONE_NO_ARG_KEY, emergencyNumbers.get(i).getPhn_no());

                                    Emergency_Contact_EditText_Fragment emergency_contact_editText_fragment = new Emergency_Contact_EditText_Fragment();
                                    emergency_contact_editText_fragment.setArguments(arg);

                                    AppCompatActivity activity = (AppCompatActivity)view.getContext();
                                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container, emergency_contact_editText_fragment).addToBackStack(null).commit();

                                    return true;

                                case R.id.menu_emergency_contact_list_item_delete_menu:
                                    AppCompatActivity actvt = (AppCompatActivity)view.getContext();

                                    viewModel_emergency_contact.deleteEmergencyContact( viewModel_emergency_contact.getUser_phn_no() );

                                    actvt.getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container, new Emergency_Contact_List_Fragment()).commit();
                                    return true;

                            }
                            return false;
                        }
                    }); //popupMenu.setOnMenuItemClickListener
                    popupMenu.show();
                }
            });  //imageButton.setOnClickListener

        }//public Holder
    }//public class Holder
}
