package com.example.londontourism;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.londontourism.Model.Review;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AttractionPreview extends AppCompatActivity {


ImageView attraction_img;
Intent intent;
ImageView star_1, star_2,star_3,star_4,star_5;
Button add_review;
TextView attraction_description, attraction_title;
String imageURL, title, description , category;
ArrayList<Review> reviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_preview);
        attraction_img = findViewById(R.id.attraction_preview_img);
        star_1 = findViewById(R.id.attraction_preview_star1);
        star_2 = findViewById(R.id.attraction_preview_star2);
        star_3 = findViewById(R.id.attraction_preview_star3);
        star_4 = findViewById(R.id.attraction_preview_star4);
        star_5 = findViewById(R.id.attraction_preview_star5);
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
    }
}