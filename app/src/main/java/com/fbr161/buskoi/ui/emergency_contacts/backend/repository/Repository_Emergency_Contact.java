package com.fbr161.buskoi.ui.emergency_contacts.backend.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fbr161.buskoi.constant.Constant;
import com.fbr161.buskoi.ui.emergency_contacts.backend.model.UsersEmergencyContact;
import com.fbr161.buskoi.ui.emergency_contacts.backend.retrofit.API_Emergency_Contact;
import com.fbr161.buskoi.ui.emergency_contacts.backend.retrofit.Retrofit_Instance_Emergency_Contact;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class Repository_Emergency_Contact {

    private API_Emergency_Contact api;

    public Repository_Emergency_Contact() {
        api = Retrofit_Instance_Emergency_Contact.getInstance().create(API_Emergency_Contact.class);
    }

    public LiveData<Boolean> insertEmergencyContact(String user_phn_no, String emergency_contact_phn_no, String emergency_contact_name){
        final MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();

        api.insertEmergencyContact(user_phn_no, emergency_contact_phn_no, emergency_contact_name).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                mutableLiveData.postValue(false);
            }
        });
        return mutableLiveData;
    }

    public LiveData<Boolean> updateEmergencyContact(String user_phn_no, String emergency_contact_phn_no, String emergency_contact_name){
        final MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();

        api.updateEmergencyContact(user_phn_no, emergency_contact_phn_no, emergency_contact_name).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                mutableLiveData.postValue(false);
            }
        });
        return mutableLiveData;
    }

    public LiveData<Boolean> deleteEmergencyContact(String user_phn_no){
        final MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>();

        api.deleteEmergencyContact(user_phn_no).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                mutableLiveData.postValue(false);
            }
        });
        return mutableLiveData;
    }


    public LiveData<UsersEmergencyContact> readEmergencyContact(String user_phn_no){
        final MutableLiveData<UsersEmergencyContact> mutableLiveData = new MutableLiveData<>();

        api.readEmergencyContact(user_phn_no).enqueue(new Callback<UsersEmergencyContact>() {
            @Override
            public void onResponse(Call<UsersEmergencyContact> call, Response<UsersEmergencyContact> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<UsersEmergencyContact> call, Throwable t) {
                Log.d("wtffff_api.readEmergencyContact", t.toString());
            }
        });
        return mutableLiveData;
    }

}
