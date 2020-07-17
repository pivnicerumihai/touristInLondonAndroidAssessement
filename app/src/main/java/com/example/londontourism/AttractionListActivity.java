package com.example.londontourism;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.londontourism.Model.Attraction;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AttractionListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference dbref;
    String list_to_show;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_list);

        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        intent = getIntent();
        list_to_show = intent.getStringExtra("Category");
        Toast.makeText(AttractionListActivity.this, list_to_show , Toast.LENGTH_SHORT).show();
        dbref = database.getReference(list_to_show);
    }

    //load data into recycler view;

    @Override
    public void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<Attraction, AttractionViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Attraction, AttractionViewHolder>(Attraction.class,R.layout.card_view_attraction,AttractionViewHolder.class,dbref) {
            @Override
            protected void populateViewHolder(AttractionViewHolder attractionViewHolder, Attraction attraction, int i) {
                attractionViewHolder.setDetails(getApplicationContext(),attraction.getTitle(),attraction.getDescription(),attraction.getImageURL());
            }
        };
        //set adapter to recycler view;
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}