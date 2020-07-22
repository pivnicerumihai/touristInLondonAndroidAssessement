package com.example.londontourism;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.londontourism.Model.Global;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    EditText email,password;
    Button register, log_in;
    LinearLayout login_anonymously;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.login_register);
        login_anonymously = findViewById(R.id.login_anonymously);
        auth = FirebaseAuth.getInstance();
        log_in = findViewById(R.id.login_btn);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);

        //Display Custom Toolbar
        Toolbar toolbar = findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);


        //Login Anonymously onClickListener
        login_anonymously.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"Loged in!",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity.this, HomeActivity.class);
                            i.putExtra("Anonymous" ,"true");
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Error!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //Register Button Listener
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Register.class);
                startActivity(i);
            }
        });

        //Login Button Listener
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    Intent i = new Intent(MainActivity.this, HomeActivity.class);

                                    startActivity(i);
                                }
                                else{
                                    Toast.makeText(MainActivity.this, "Authentication failed!" , Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        };


    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);
        }
    }

    }


