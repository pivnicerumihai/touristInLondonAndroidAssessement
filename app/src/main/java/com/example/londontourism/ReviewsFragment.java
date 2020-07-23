package com.example.londontourism;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.londontourism.Model.Review;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class ReviewsFragment extends Fragment {

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference dbref;
    Bundle bundle;
    String category,title;
    CardView cv;

    public ReviewsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reviews, container, false);
        bundle =getArguments();
        category = bundle.getString("Category");
        title = bundle.getString("Title");

        firebaseDatabase = FirebaseDatabase.getInstance();
        dbref = firebaseDatabase.getReference(category).child(title).child("reviews");

        recyclerView = view.findViewById(R.id.reviews_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        final FirebaseRecyclerAdapter<Review,ReviewViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Review, ReviewViewHolder>(Review.class,R.layout.card_view_review,ReviewViewHolder.class, dbref) {



            @Override
            protected void populateViewHolder(ReviewViewHolder reviewViewHolder, Review review, int i) {

                reviewViewHolder.setReviewDetails(getContext(),review.getReview_description(),review.getAdded_by(),review.getStars());


            }

        };

    recyclerView.setAdapter(firebaseRecyclerAdapter);

        return view;
    }
}