package com.fbr161.buskoi;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fbr161.buskoi.signIn_signUp.sign_in.SignIn_Fragment;
import com.fbr161.buskoi.signIn_signUp.sign_up.SignUp_Fragment;

public class SignIn_SignUp_Activity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_sign_up);

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.signIn_signUp_fragement_container, new SignIn_Fragment()).commit();
        }

    }
}