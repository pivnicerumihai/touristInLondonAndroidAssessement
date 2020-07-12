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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        user_ref =FirebaseDatabase.getInstance().getReference("_user_").orderByChild("email_address").equalTo(user.getEmail());
        user_ref.addListenerForSingleValueEvent(listener);

        Toolbar toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  new CategoryFragment()).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.nav_categories:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,  new CategoryFragment()).commit();
                break;
            case R.id.nav_log_out:
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
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
            for(DataSnapshot dss : snapshot.getChildren()){
                loged_user = dss.getValue(Users.class);

            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };



}