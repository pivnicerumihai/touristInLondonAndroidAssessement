package com.example.londontourism;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class AccountSettingsFragment extends Fragment {

    ImageButton btn_change_email;
    ImageButton btn_change_password;
    Boolean update_email;
    Boolean update_password;
    EditText et_user_email;
    EditText et_user_password;
    Button apply_changes;
    String user_email;
    FirebaseUser user;
    Activity activity;
    DatabaseReference user_email_db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_account_settings, container, false);
    }
        @Override
        public void onStart(){
        super.onStart();
            View view = getView();
            if(view != null) {
                apply_changes = view.findViewById(R.id.apply_changes);
                et_user_email = (EditText) view.findViewById(R.id.change_email);
                et_user_password = (EditText) view.findViewById(R.id.change_password);
                btn_change_email = (ImageButton) view.findViewById(R.id.btn_change_email);
                btn_change_password = (ImageButton) view.findViewById(R.id.btn_change_password);
                update_email = false;
                update_password = false;
                user = FirebaseAuth.getInstance().getCurrentUser();
                user_email = user.getEmail();
                user_email_db = FirebaseDatabase.getInstance().getReference("_user_").child(user.getUid()).child("email_address");
                activity = getActivity();
                Log.i("USER EMAIL:", user_email);
                et_user_email.setText(user_email, TextView.BufferType.EDITABLE);



                btn_change_email.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!(user_email.equals(et_user_email.getText().toString()))) {
                            update_email = !update_email;
                            if (update_email) {
                                btn_change_email.setColorFilter(getResources().getColor(R.color.black));
                                btn_change_email.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                                Toast.makeText(activity, "Email Ready To Update!", Toast.LENGTH_SHORT).show();
                                ;
                            } else {
                                btn_change_email.setColorFilter(getResources().getColor(R.color.white));
                                btn_change_email.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                            }
                        }
                        else{
                            Toast.makeText(activity,"Email Address did not change!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                btn_change_password.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        update_password = !update_password;
                        if(update_password){
                            btn_change_password.setColorFilter(getResources().getColor(R.color.black));
                            btn_change_password.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                            Toast.makeText(activity, "Password Ready To Update!", Toast.LENGTH_SHORT).show();;
                        }
                        else{
                            btn_change_password.setColorFilter(getResources().getColor(R.color.white));
                            btn_change_password.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                        }
                    }
                });

                apply_changes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(update_email && update_password){
                            user.updateEmail(et_user_email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    user.updatePassword(et_user_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                btn_change_email.setColorFilter(getResources().getColor(R.color.white));
                                                btn_change_email.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                                                btn_change_password.setColorFilter(getResources().getColor(R.color.white));
                                                btn_change_password.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                                                user_email_db.setValue(et_user_email.getText().toString());
                                            Toast.makeText(activity, "Email And Password Successfully Updated!", Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(activity, "You have been signed in for too long, you must Sign Out and Sign In again to update your user credentials", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                                }
                            });
                        }
                        else if(update_email && !update_password){
                            user.updateEmail(et_user_email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        btn_change_email.setColorFilter(getResources().getColor(R.color.white));
                                        btn_change_email.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                                    user_email_db.setValue(et_user_email.getText().toString());
                                    Toast.makeText(activity, "Email Updated!", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                        Toast.makeText(activity, "You have been signed in for too long, you must Sign Out and Sign In again to update your user credentials", Toast.LENGTH_LONG).show();}
                                }
                            });
                        }
                        else if(!update_email && update_password){
                            user.updatePassword(et_user_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        btn_change_password.setColorFilter(getResources().getColor(R.color.white));
                                        btn_change_password.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                                    Toast.makeText(activity, "Password Updated!",Toast.LENGTH_SHORT).show();
                                }}
                            });
                        }
                    }
                });
        }



}}