package com.fbr161.buskoi.ui.emergency_contacts.backend.retrofit;

import com.fbr161.buskoi.constant.Constant;
import com.fbr161.buskoi.ui.emergency_contacts.backend.model.UsersEmergencyContact;
import com.fbr161.buskoi.ui.home.backed.model.BookedBus;
import com.fbr161.buskoi.ui.home.backed.model.BusLocation;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface API_Emergency_Contact {

    @FormUrlEncoded
    @POST(Constant.Url.EMERGENCY_CONTACT_INSERT_API)
    Call<Boolean> insertEmergencyContact(
            @Field("user_phn_no") String user_phn_no,
            @Field("emergency_contact_phn_no") String emergency_contact_phn_no,
            @Field("emergency_contact_name") String emergency_contact_name
    );

    @FormUrlEncoded
    @POST(Constant.Url.EMERGENCY_CONTACT_READ_API)
    Call<UsersEmergencyContact> readEmergencyContact(
            @Field("user_phn_no") String user_phn_no
    );

    @FormUrlEncoded
    @POST(Constant.Url.EMERGENCY_CONTACT_UPDATE_API)
    Call<Boolean> updateEmergencyContact(
            @Field("user_phn_no") String user_phn_no,
            @Field("emergency_contact_phn_no") String emergency_contact_phn_no,
            @Field("emergency_contact_name") String emergency_contact_name
    );

    @FormUrlEncoded
    @POST(Constant.Url.EMERGENCY_CONTACT_DELETE_API)
    Call<Boolean> deleteEmergencyContact(
            @Field("user_phn_no") String user_phn_no
    );
}
