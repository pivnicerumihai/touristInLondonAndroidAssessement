package com.example.londontourism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.solver.widgets.Snapshot;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.londontourism.Model.Review;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;

public class AttractionPreview extends AppCompatActivity {


ImageView attraction_img;
Intent intent;
ImageView star_1, star_2,star_3,star_4,star_5;
Button add_review;
TextView attraction_description, attraction_title, total_reviews;
String imageURL, title, description , category;
ArrayList<Review> reviews = new ArrayList<>();
long total_stars = 0;
long average_review;
Query dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_preview);

        Toolbar toolbar = findViewById(R.id.preview_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        attraction_img = findViewById(R.id.attraction_preview_img);
        star_1 = findViewById(R.id.attraction_preview_star1);
        star_2 = findViewById(R.id.attraction_preview_star2);
        star_3 = findViewById(R.id.attraction_preview_star3);
        star_4 = findViewById(R.id.attraction_preview_star4);
        star_5 = findViewById(R.id.attraction_preview_star5);
        total_reviews = findViewById(R.id.total_reviews);
        add_review = findViewById(R.id.attraction_preview_btn_add_review);
        attraction_description = findViewById(R.id.attraction_preview_description);
        attraction_title = findViewById(R.id.attraction_preview_title);
        intent = getIntent();
        title = intent.getStringExtra("Title");
        imageURL = intent.getStringExtra("imageURL");
        description = intent.getStringExtra("Description");
        category = intent.getStringExtra("Category");

        attraction_title.setText(title);
        attraction_description.setText(description);
        Picasso.get().load(imageURL).into(attraction_img);

        ReviewsFragment reviewsFragment = new ReviewsFragment();

        Bundle bundle = new Bundle();
        bundle.putString("Title", title);
        bundle.putString("Category", category);
        reviewsFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.review_fragment, reviewsFragment).commit();
        add_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AttractionPreview.this,CreateReviewActivity.class);
                i.putExtra("Category", category);
                i.putExtra("Title",title);
                i.putExtra("imageURL",imageURL);
                startActivity(i);
            }
        });

    dbref = FirebaseDatabase.getInstance().getReference(category).child(title).child("reviews").orderByChild("stars");
    dbref.addListenerForSingleValueEvent(listener);
    }

ValueEventListener listener = new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {


        for(DataSnapshot dss: snapshot.getChildren()){
            Review r = dss.getValue(Review.class);
            total_stars = total_stars + r.getStars();
            reviews.add(r);
        }
        if(reviews.size() != 0){
            total_reviews.setText(" (" + reviews.size() + ")");
            average_review = total_stars / reviews.size();
            if(average_review == 5){
                star_1.setImageResource(R.drawable.ic_baseline_star_24);
                star_2.setImageResource(R.drawable.ic_baseline_star_24);
                star_3.setImageResource(R.drawable.ic_baseline_star_24);
                star_4.setImageResource(R.drawable.ic_baseline_star_24);
                star_5.setImageResource(R.drawable.ic_baseline_star_24);
            }

            else if(average_review == 4){
                star_1.setImageResource(R.drawable.ic_baseline_star_24);
                star_2.setImageResource(R.drawable.ic_baseline_star_24);
                star_3.setImageResource(R.drawable.ic_baseline_star_24);
                star_4.setImageResource(R.drawable.ic_baseline_star_24);
                star_5.setImageResource(R.drawable.ic_baseline_star_border_24);
            }

            else if(average_review == 3){
                star_1.setImageResource(R.drawable.ic_baseline_star_24);
                star_2.setImageResource(R.drawable.ic_baseline_star_24);
                star_3.setImageResource(R.drawable.ic_baseline_star_24);
                star_4.setImageResource(R.drawable.ic_baseline_star_border_24);
                star_5.setImageResource(R.drawable.ic_baseline_star_border_24);

            }

            else if(average_review == 2){
                star_1.setImageResource(R.drawable.ic_baseline_star_24);
                star_2.setImageResource(R.drawable.ic_baseline_star_24);
                star_3.setImageResource(R.drawable.ic_baseline_star_border_24);
                star_4.setImageResource(R.drawable.ic_baseline_star_border_24);
                star_5.setImageResource(R.drawable.ic_baseline_star_border_24);
            }

            else if(average_review == 1){
                star_1.setImageResource(R.drawable.ic_baseline_star_24);
                star_2.setImageResource(R.drawable.ic_baseline_star_border_24);
                star_3.setImageResource(R.drawable.ic_baseline_star_border_24);
                star_4.setImageResource(R.drawable.ic_baseline_star_border_24);
                star_5.setImageResource(R.drawable.ic_baseline_star_border_24);
            }

        }
      else {
          star_1.setImageResource(R.drawable.ic_baseline_star_border_24);
            star_2.setImageResource(R.drawable.ic_baseline_star_border_24);
            star_3.setImageResource(R.drawable.ic_baseline_star_border_24);
            star_4.setImageResource(R.drawable.ic_baseline_star_border_24);
            star_5.setImageResource(R.drawable.ic_baseline_star_border_24);
          total_reviews.setText(" (0)");
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
};
}