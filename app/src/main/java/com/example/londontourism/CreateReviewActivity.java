package com.example.londontourism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.londontourism.Model.Global;
import com.example.londontourism.Model.Review;
import com.example.londontourism.Model.Users;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class CreateReviewActivity extends AppCompatActivity {

    Integer stars = null;
    Intent intent;
    String img_url,title, category;
    EditText review_description;
    ImageView review_img,star_1,star_2,star_3,star_4,star_5;
    TextView review_title;
    Button btn_submit;
    DatabaseReference dbref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_review);

        Toolbar toolbar = findViewById(R.id.create_review_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        intent = getIntent();
        title = intent.getStringExtra("Title");
        img_url = intent.getStringExtra("imageURL");
        category = intent.getStringExtra("Category");
        review_img = findViewById(R.id.create_review_img);
        review_title = findViewById(R.id.create_review_title);
        review_description = findViewById(R.id.create_review_description);

        btn_submit = findViewById(R.id.submit_review);

        dbref = FirebaseDatabase.getInstance().getReference(category).child(title).child("reviews");
        star_1 = findViewById(R.id.create_review_star1);
        star_2 = findViewById(R.id.create_review_star2);
        star_3 = findViewById(R.id.create_review_star3);
        star_4 = findViewById(R.id.create_review_star4);
        star_5 = findViewById(R.id.create_review_star5);
        review_title.setText(title);
        Picasso.get().load(img_url).into(review_img);
        final Global global_user_details = (Global) getApplicationContext();
        final String loged_user_name = global_user_details.getName();


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(stars != null){
                Review review =new Review(review_description.getText().toString(),loged_user_name,stars.longValue());
                dbref.child(dbref.push().getKey()).setValue(review).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(CreateReviewActivity.this, "Review Added!", Toast.LENGTH_SHORT).show();
               finishActivity(105);

                    }
                });
            }
            else{
                Toast.makeText(CreateReviewActivity.this,"You have not selected a number of stars for your review!", Toast.LENGTH_LONG).show();
                }
            }
        });

        star_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stars = 1;
                star_1.setImageResource(R.drawable.ic_baseline_star_24);
                star_2.setImageResource(R.drawable.ic_baseline_star_border_24);
                star_3.setImageResource(R.drawable.ic_baseline_star_border_24);
                star_4.setImageResource(R.drawable.ic_baseline_star_border_24);
                star_5.setImageResource(R.drawable.ic_baseline_star_border_24);
            }
        });

        star_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stars = 2;
                star_1.setImageResource(R.drawable.ic_baseline_star_24);
                star_2.setImageResource(R.drawable.ic_baseline_star_24);
                star_3.setImageResource(R.drawable.ic_baseline_star_border_24);
                star_4.setImageResource(R.drawable.ic_baseline_star_border_24);
                star_5.setImageResource(R.drawable.ic_baseline_star_border_24);
            }
        });
        star_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stars = 3;
                star_1.setImageResource(R.drawable.ic_baseline_star_24);
                star_2.setImageResource(R.drawable.ic_baseline_star_24);
                star_3.setImageResource(R.drawable.ic_baseline_star_24);
                star_4.setImageResource(R.drawable.ic_baseline_star_border_24);
                star_5.setImageResource(R.drawable.ic_baseline_star_border_24);
            }
        });
        star_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stars = 4;
                star_1.setImageResource(R.drawable.ic_baseline_star_24);
                star_2.setImageResource(R.drawable.ic_baseline_star_24);
                star_3.setImageResource(R.drawable.ic_baseline_star_24);
                star_4.setImageResource(R.drawable.ic_baseline_star_24);
                star_5.setImageResource(R.drawable.ic_baseline_star_border_24);
            }
        });
        star_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stars = 5;
                star_1.setImageResource(R.drawable.ic_baseline_star_24);
                star_2.setImageResource(R.drawable.ic_baseline_star_24);
                star_3.setImageResource(R.drawable.ic_baseline_star_24);
                star_4.setImageResource(R.drawable.ic_baseline_star_24);
                star_5.setImageResource(R.drawable.ic_baseline_star_24);
            }
        });

    }

}