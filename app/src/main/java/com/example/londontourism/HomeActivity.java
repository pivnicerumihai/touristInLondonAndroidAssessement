package com.example.londontourism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.solver.widgets.Snapshot;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.londontourism.Model.Global;
import com.example.londontourism.Model.Users;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    private Query user_ref;
    Users loged_user;
    TextView loged_user_name;
    TextView loged_user_email;
    View headerView;
    Global global_user_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        drawer = findViewById(R.id.drawer_layout);
        user_ref =FirebaseDatabase.getInstance().getReference("_user_").orderByChild("email_address").equalTo(user.getEmail());
        user_ref.addListenerForSingleValueEvent(listener);
        global_user_details = (Global) getApplicationContext();
        //Display Custom Toolbar;
        Toolbar toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //get IDs for username and emails from nav_header
        headerView = navigationView.getHeaderView(0);
        loged_user_name = headerView.findViewById(R.id.loged_user_name);
        loged_user_email = headerView.findViewById(R.id.loged_user_email);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  new CategoryFragment()).commit();

        //if loged with credentials,set email and name to Views;

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.nav_categories:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  new CategoryFragment()).commit();
                break;
            case R.id.nav_account_settings:
                if(!(getIntent().hasExtra("Anonymous"))) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccountSettingsFragment()).commit();
                }
                else{
                    Toast.makeText(this, "You must be loged in with email and password in order to access Account Settings", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.nav_log_out:
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
        super.onBackPressed();
    }}
    public void onStart(){
        super.onStart();

    }
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for(DataSnapshot dss : snapshot.getChildren()) {
                loged_user = dss.getValue(Users.class);
                if (!(getIntent().hasExtra("Anonymous"))) {

                    global_user_details.setName(loged_user.getFirst_name() + " " + loged_user.getLast_name());
                    global_user_details.setEmail(loged_user.getEmail_address());

                    loged_user_name.setText(loged_user.getFirst_name());
                    loged_user_email.setText(loged_user.getEmail_address());
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };



}