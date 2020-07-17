package com.example.londontourism;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.londontourism.Model.Attraction;

public class CategoryFragment extends Fragment implements View.OnClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category,container,false);

        final Button btn_attraction = (Button) view.findViewById(R.id.btn_category_attraction);
        Button btn_museum =  view.findViewById(R.id.btn_category_museums);
        Button btn_parks=  view.findViewById(R.id.btn_category_parks);
        Button btn_other =  view.findViewById(R.id.btn_category_other);

        btn_attraction.setOnClickListener(this);
        btn_museum.setOnClickListener(this);
        btn_parks.setOnClickListener(this);
        btn_other.setOnClickListener(this);


        return view;


    }

     //OnClick method for category buttons
    @Override
    public void onClick(View view) {

        Intent i = new Intent(getActivity(),AttractionListActivity.class);

        switch (view.getId()){
            case R.id.btn_category_attraction:

              i.putExtra("Category","_attractions_");
              startActivity(i);
        break;
            case R.id.btn_category_museums:

                i.putExtra("Category","_museums_");
                startActivity(i);
                break;
            case R.id.btn_category_parks:

                i.putExtra("Category","_parks_");
                startActivity(i);
                break;
            case R.id.btn_category_other:

                i.putExtra("Category","_others_");
                startActivity(i);
                break;
            default:
                break;
        }

        }

    }
