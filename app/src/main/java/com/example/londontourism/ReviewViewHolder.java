package com.example.londontourism;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewViewHolder extends RecyclerView.ViewHolder {
    View view;


    public ReviewViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
    }
    

    public void setReviewDetails(Context context, final String review_description,final String added_by,final Long stars){



        //Views

        TextView review_description_view = view.findViewById(R.id.review_description);
        TextView review_author = view.findViewById(R.id.reviewed_by);
        ImageView star_1 = view.findViewById(R.id.review_star1);
        ImageView star_2 = view.findViewById(R.id.review_star2);
        ImageView star_3 = view.findViewById(R.id.review_star3);
        ImageView star_4 = view.findViewById(R.id.review_star4);
        ImageView star_5 = view.findViewById(R.id.review_star5);

        //Check number of stars

        if(stars == 5){
           star_1.setImageResource(R.drawable.ic_baseline_star_24);
            star_2.setImageResource(R.drawable.ic_baseline_star_24);
            star_3.setImageResource(R.drawable.ic_baseline_star_24);
            star_4.setImageResource(R.drawable.ic_baseline_star_24);
            star_5.setImageResource(R.drawable.ic_baseline_star_24);
        }

        else if(stars == 4){
            star_1.setImageResource(R.drawable.ic_baseline_star_24);
            star_2.setImageResource(R.drawable.ic_baseline_star_24);
            star_3.setImageResource(R.drawable.ic_baseline_star_24);
            star_4.setImageResource(R.drawable.ic_baseline_star_24);
            star_5.setImageResource(R.drawable.ic_baseline_star_border_24);
        }

        else if(stars == 3){
            star_1.setImageResource(R.drawable.ic_baseline_star_24);
            star_2.setImageResource(R.drawable.ic_baseline_star_24);
            star_3.setImageResource(R.drawable.ic_baseline_star_24);
            star_4.setImageResource(R.drawable.ic_baseline_star_border_24);
            star_5.setImageResource(R.drawable.ic_baseline_star_border_24);

        }

        else if(stars == 2){
            star_1.setImageResource(R.drawable.ic_baseline_star_24);
            star_2.setImageResource(R.drawable.ic_baseline_star_24);
            star_3.setImageResource(R.drawable.ic_baseline_star_border_24);
            star_4.setImageResource(R.drawable.ic_baseline_star_border_24);
            star_5.setImageResource(R.drawable.ic_baseline_star_border_24);
        }

        else if(stars == 1){
            star_1.setImageResource(R.drawable.ic_baseline_star_24);
            star_2.setImageResource(R.drawable.ic_baseline_star_border_24);
            star_3.setImageResource(R.drawable.ic_baseline_star_border_24);
            star_4.setImageResource(R.drawable.ic_baseline_star_border_24);
            star_5.setImageResource(R.drawable.ic_baseline_star_border_24);
        }


        review_description_view.setText(review_description);
        review_author.setText(added_by);


    }
}
