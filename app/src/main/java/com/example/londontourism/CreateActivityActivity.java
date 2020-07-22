package com.example.londontourism;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.londontourism.Model.Attraction;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;

public class CreateActivityActivity extends AppCompatActivity {

    ArrayAdapter<CharSequence> adapter;
    Spinner category_spinner;
    LottieAnimationView img_selection;
    ImageView uploaded_img;
    EditText activity_title;
    EditText activity_description;
    Button create_activity;
    Uri img_path;
    StorageReference storageReference;
    String category;
    DatabaseReference category_reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_activity);
        category_spinner = findViewById(R.id.create_activity_spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category_spinner.setAdapter(adapter);
        category = null;

        category_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    category = category_spinner.getSelectedItem().toString();
                Log.i("SELECTED:",category_spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                    category = null;
            }
        });

        storageReference = FirebaseStorage.getInstance().getReference();
        uploaded_img = findViewById(R.id.uploaded_img);
        img_selection = findViewById(R.id.img_selection);
        activity_title = findViewById(R.id.create_activity_title);
        activity_description = findViewById(R.id.create_activity_description);
        create_activity = findViewById(R.id.create_activity_btn);

        create_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(activity_title.getText().toString().length() < 5){
                        Toast.makeText(CreateActivityActivity.this, "Activity Title must be at least 5 characters long" , Toast.LENGTH_SHORT).show();
                    }
                    else if(activity_description.getText().toString().length() < 5){
                        Toast.makeText(CreateActivityActivity.this, "Activity Description must be at least 5 characters long" , Toast.LENGTH_SHORT).show();
                    }
                    else if(category == null || category.equals("Select Category")){
                        Toast.makeText(CreateActivityActivity.this, "You did not select a category" , Toast.LENGTH_SHORT).show();
                    }
                    else{
                        DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_user_");
                        final String id =dbref.push().getKey();
                        final StorageReference reference = storageReference.child(id + "." + getExtension(img_path));
                        reference.putFile(img_path).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String url = uri.toString();

                                        if(category.equals("Parks and Nature")){
                                                category_reference = FirebaseDatabase.getInstance().getReference("_parks_");
                                        }
                                        else if(category.equals("Other Activities")){
                                            category_reference = FirebaseDatabase.getInstance().getReference("_others_");
                                        }
                                        Attraction attraction = new Attraction(activity_description.getText().toString(), url , activity_title.getText().toString());
                                        category_reference.child(activity_title.getText().toString()).setValue(attraction);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(CreateActivityActivity.this, "Failed to get URL", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        });
                    }
            }
        });

        img_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, 105);
            }
        });
    }
@Override
    protected  void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 105 && resultCode == RESULT_OK && data.getData()!= null){
                img_path = data.getData();
                uploaded_img.setVisibility(View.VISIBLE);
                img_selection.setVisibility(View.INVISIBLE);
                img_selection.clearAnimation();
            Picasso.get().load(data.getData()).fit().into(uploaded_img);
        }
}

    private String getExtension(Uri img_path){
        ContentResolver resolver = getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(resolver.getType(img_path));
    };

}