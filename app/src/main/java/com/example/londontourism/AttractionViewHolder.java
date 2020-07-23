package com.example.londontourism;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.londontourism.Model.Review;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class AttractionViewHolder extends RecyclerView.ViewHolder {

    View view;
    Intent i;
    public AttractionViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;

    }

    public void setDetails(Context context, final String title, final String description, final String imageURL, final String category){
        //Views
        TextView attraction_title = view.findViewById(R.id.card_attraction_title);
        ImageView attraction_image = view.findViewById(R.id.card_imageView);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(view.getContext(), AttractionPreview.class);
                i.putExtra("imageURL", imageURL);
                i.putExtra("Title",title);
                i.putExtra("Description", description);
                i.putExtra("Category", category);
                view.getContext().startActivity(i);
            }
        });
        //add data to views
        attraction_title.setText(title);
        Picasso.get().load(imageURL).into(attraction_image);
    }

}
