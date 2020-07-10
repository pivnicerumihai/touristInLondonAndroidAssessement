package com.example.londontourism;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.londontourism.Model.Users;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText first_name,last_name,email,confirm_email,password,confirm_password;
    Button register;

    DatabaseReference dbref;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        first_name = findViewById(R.id.reg_first_name);
        last_name = findViewById(R.id.reg_last_name);
        email = findViewById(R.id.reg_email);
        confirm_email = findViewById(R.id.reg_email2);
        password = findViewById(R.id.reg_password);
        confirm_password = findViewById(R.id.reg_password2);
        register = findViewById(R.id.reg_register);
        dbref = FirebaseDatabase.getInstance().getReference("_user_");

        //Display Custom Toolbar
        Toolbar toolbar = findViewById(R.id.register_toolbar);
        setSupportActionBar(toolbar);

        //Add Up Button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(first_name.getText().length() < 2){
                    Toast.makeText(Register.this, "First Name must be at least 2 letters long!", Toast.LENGTH_SHORT).show();
                }
                else if(last_name.getText().length() < 2){
                    Toast.makeText(Register.this, "Last Name must be at least 2 letters long!", Toast.LENGTH_SHORT).show();
                }
                else if(!email.getText().toString().equals(confirm_email.getText().toString())){
                    Toast.makeText(Register.this, "E-mails do not match!", Toast.LENGTH_SHORT).show();
                }
                else if(!password.getText().toString().equals(confirm_password.getText().toString())){
                    Toast.makeText(Register.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
                }
                else if(password.getText().length() < 6){
                    Toast.makeText(Register.this,"Password must be at least 6 characters long!",Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                saveUserToDB(first_name.getText().toString(),last_name.getText().toString(),email.getText().toString());
                                Toast.makeText(Register.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(Register.this,"Error!" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

    }
    private void saveUserToDB(String first_name, String last_name, String e_mail){
        Users user = new Users(first_name,last_name,e_mail);
        dbref.child(dbref.push().getKey()).setValue(user);
    }

}