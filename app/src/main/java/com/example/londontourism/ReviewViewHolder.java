package com.example.londontourism;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    View view;


    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
    }

    public void setReviewDetails(Context context, String ReviewDescription, String added_by, String stars){
        //Views
        Log.i("COMING THROUGH!","COMING THROUGH!");
        TextView review_description = view.findViewById(R.id.review_description);
        TextView review_author = view.findViewById(R.id.reviewed_by);
        review_description.setText(ReviewDescription);
        review_author.setText(added_by);


    }
}
