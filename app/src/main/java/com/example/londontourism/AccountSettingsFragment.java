package com.example.londontourism;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class AccountSettingsFragment extends Fragment {

    EditText et_user_email;
    EditText et_user_password;
    Button apply_changes;
    String user_email;
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
                user_email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                Log.i("USER EMAIL:", user_email);
                et_user_email.setText(user_email, TextView.BufferType.EDITABLE);
                apply_changes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        
                    }
                });
        }



}}