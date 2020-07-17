package com.example.londontourism;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class AttractionViewHolder extends RecyclerView.ViewHolder {

    View view;

    public AttractionViewHolder(@NonNull View itemView) {
        super(itemView);

        view = itemView;
    }

    public void setDetails(Context context, String title, String description, String imageURL){
        //Views
        TextView attraction_title = view.findViewById(R.id.card_attraction_title);
        ImageView attraction_image = view.findViewById(R.id.card_imageView);

        //add data to views
        attraction_title.setText(title);
        Picasso.get().load(imageURL).into(attraction_image);
    }
}
