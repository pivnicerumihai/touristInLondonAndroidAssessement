package com.example.londontourism;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.londontourism.Model.Attraction;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AttractionListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference dbref;
    String list_to_show;
    Intent intent;
    CardView cv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_list);



        Toolbar toolbar = findViewById(R.id.list_toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database = FirebaseDatabase.getInstance();
        intent = getIntent();
        list_to_show = intent.getStringExtra("Category");
        cv = findViewById(R.id.attraction_cardview);
        dbref = database.getReference(list_to_show);

        if(list_to_show.equals("_parks_") || list_to_show.equals("_others_")){
            Toast.makeText(AttractionListActivity.this, "You can add London Activities to the App by clicking on the + button on the bottom right", Toast.LENGTH_LONG).show();
            ImageButton add_review_button = new ImageButton(this);
            Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
            layoutParams.gravity = Gravity.END;
            layoutParams.setMargins(0 , 0 ,16, 0);
            add_review_button.setLayoutParams(layoutParams);
            add_review_button.setImageResource(R.drawable.ic_baseline_add_24);
            add_review_button.setBackgroundResource(R.color.colorPrimary);

            toolbar.addView(add_review_button);
            add_review_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(AttractionListActivity.this, CreateActivityActivity.class);
                    startActivity(i);
                }
            });
        }
    }

    //load data into recycler view;

    @Override
    public void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<Attraction, AttractionViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Attraction, AttractionViewHolder>(Attraction.class,R.layout.card_view_attraction,AttractionViewHolder.class,dbref) {
            @Override
            protected void populateViewHolder(AttractionViewHolder attractionViewHolder, Attraction attraction, int i) {
                attractionViewHolder.setDetails(getApplicationContext(),attraction.getTitle(),attraction.getDescription(),attraction.getImageURL(), list_to_show);
            }
        };
        //set adapter to recycler view;
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}