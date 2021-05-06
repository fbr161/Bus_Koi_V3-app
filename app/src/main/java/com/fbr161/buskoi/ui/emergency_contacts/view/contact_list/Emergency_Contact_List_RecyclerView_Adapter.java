package com.fbr161.buskoi.ui.emergency_contacts.view.contact_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.fbr161.buskoi.R;
import com.fbr161.buskoi.ui.emergency_contacts.backend.model.UsersEmergencyContact;

import java.util.ArrayList;

public class Emergency_Contact_List_RecyclerView_Adapter extends RecyclerView.Adapter<Emergency_Contact_List_RecyclerView_Adapter.Holder>{

    ArrayList<UsersEmergencyContact> emergencyNumbers = new ArrayList<>();
    Context context;

    public Emergency_Contact_List_RecyclerView_Adapter(ArrayList<UsersEmergencyContact> emergencyNumbers, Context context) {
        this.emergencyNumbers = emergencyNumbers;
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
                                    Toast.makeText(context, i+"", Toast.LENGTH_SHORT).show();
                                    return true;

                                case R.id.menu_emergency_contact_list_item_delete_menu:
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
