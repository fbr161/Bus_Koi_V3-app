package com.fbr161.buskoi;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.fbr161.buskoi.ui.home.HomeFragment;
import com.fbr161.buskoi.ui.purchase_ticket.PurchaseTicketFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
//import com.google.android.material.navigation.NavigationView;
//import android.support.design.widget.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

////////////////
import com.fbr161.buskoi.constant.Constant;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawer;
    Toolbar toolbar;

    //user info
    private String user_name="";
    private String user_phn_no="";
    //design
    private TextView user_name_txtView, user_phn_no_txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Bus Koi");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        /////////////////////////Shared preference
        SharedPreferences sign_in_pref = getSharedPreferences(Constant.LOG_IN_SHARED_PREF, MODE_PRIVATE);
        user_name = sign_in_pref.getString(Constant.SHARED_PREF_KEY___NAME, "");
        user_phn_no = sign_in_pref.getString(Constant.SHARED_PREF_KEY___PHONE_NO, "");



        ////////////////////////////
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        user_name_txtView = (TextView) headerView.findViewById(R.id.nav_header_user_name);
        user_phn_no_txtView = (TextView) headerView.findViewById(R.id.nav_header_user_phn_no);
        user_name_txtView.setText(user_phn_no);
        user_phn_no_txtView.setText("");


        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.menu_home);
        }



        //////////////////////////////
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_purchase_ticket)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch(menuItem.getItemId()){

            case R.id.menu_home:
                toolbar.setTitle("Bus Koi");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container, new HomeFragment()).commit();
                break;

            case R.id.menu_ticket:
                toolbar.setTitle("Purchase Ticket");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragement_container, new PurchaseTicketFragment()).commit();
                break;

            case R.id.menu_emergency_contact_number:
                //Intent intent = new Intent(this,SOS.class);
                //startActivity(intent);
                Toast.makeText(this, "Emergency", Toast.LENGTH_SHORT).show();
                break;


            case R.id.menu_logout:
//                Intent intnt_sign_in = new Intent(this, SignUp_SignIn_Activity.class);
//                intnt_sign_in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(intnt_sign_in);
//
//                SharedPreferences sp = getSharedPreferences(SignUp_SignIn_Activity.LOG_IN_SHARED_PREF, MODE_PRIVATE);
//                SharedPreferences.Editor editor = sp.edit();
//                editor.putBoolean(Constant.SHARED_PREF_KEY___LOGIN_STATUS, false);
//                editor.putString(Constant.SHARED_PREF_KEY___PHONE_NO,"");
//                editor.putString(Constant.SHARED_PREF_KEY___NAME,"");
//                editor.apply();
                Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
                break;

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else if(getSupportFragmentManager().getBackStackEntryCount()>0) {
            super.onBackPressed();
        }
        else{
            exit_pop_up();
        }
    }

    ////////////////

    public void exit_pop_up(){

        AlertDialog.Builder mBldr=new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.exit_pop_up,null);

        mBldr.setView(mView);
        final AlertDialog dialog= mBldr.create();
        dialog.show();

        TextView exit_yes_bttn = (TextView) mView.findViewById(R.id.exit_yes_textVw);
        TextView exit_no_bttn = (TextView) mView.findViewById(R.id.exit_no_textVw);

        exit_yes_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });

        exit_no_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}