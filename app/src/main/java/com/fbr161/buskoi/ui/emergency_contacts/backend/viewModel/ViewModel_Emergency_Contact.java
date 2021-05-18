package com.fbr161.buskoi.ui.emergency_contacts.backend.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fbr161.buskoi.ui.emergency_contacts.backend.model.UsersEmergencyContact;
import com.fbr161.buskoi.ui.emergency_contacts.backend.repository.Repository_Emergency_Contact;

public class ViewModel_Emergency_Contact extends ViewModel{

    MutableLiveData<UsersEmergencyContact> users_emergency_contact_MutableLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> insertEmergencyContact_status = new MutableLiveData<>();
    MutableLiveData<Boolean> updateEmergencyContact_status = new MutableLiveData<>();
    MutableLiveData<Boolean> deleteEmergencyContact_status = new MutableLiveData<>();

    String user_phn_no;

    Repository_Emergency_Contact repository_emergency_contact;

    public ViewModel_Emergency_Contact() {
        repository_emergency_contact = new Repository_Emergency_Contact();

        insertEmergencyContact_status.postValue(false);
        updateEmergencyContact_status.postValue(false);
        deleteEmergencyContact_status.postValue(false);

    }

    public LiveData<Boolean> insertEmergencyContact(String user_phn_no, String emergency_contact_phn_no, String emergency_contact_name){

        insertEmergencyContact_status = (MutableLiveData<Boolean>) repository_emergency_contact.insertEmergencyContact(user_phn_no, emergency_contact_phn_no, emergency_contact_name);

        return insertEmergencyContact_status;
    }

    public LiveData<UsersEmergencyContact> readEmergencyContact(String user_phn_no){

        users_emergency_contact_MutableLiveData = (MutableLiveData<UsersEmergencyContact>) repository_emergency_contact.readEmergencyContact(user_phn_no);

        return users_emergency_contact_MutableLiveData;
    }

    public LiveData<Boolean> updateEmergencyContact(String user_phn_no, String emergency_contact_phn_no, String emergency_contact_name){

        updateEmergencyContact_status = (MutableLiveData<Boolean>) repository_emergency_contact.updateEmergencyContact(user_phn_no, emergency_contact_phn_no, emergency_contact_name);

        return updateEmergencyContact_status;
    }

    public LiveData<Boolean> deleteEmergencyContact(String user_phn_no){

        deleteEmergencyContact_status = (MutableLiveData<Boolean>) repository_emergency_contact.deleteEmergencyContact(user_phn_no);

        return deleteEmergencyContact_status;
    }

    public void setUser_phn_no(String user_phn_no) {
        this.user_phn_no = user_phn_no;
    }

    public String getUser_phn_no() {
        return user_phn_no;
    }
}
