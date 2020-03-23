package com.example.gameappver0;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;
import com.example.gameappver0.HomeFragment.PenaltyServices;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.gameappver0.data.AppDatabase;
import com.example.gameappver0.data.Penalty;
import com.example.gameappver0.data.PenaltyDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class addPenaltyFragment extends Fragment {
    private EditText txt_title,txt_coins,txt_description;
    public Button bn_add;

    public addPenaltyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_add_penalty, container, false);
        txt_title = view.findViewById(R.id.txt_penalty_title);
        txt_coins = view.findViewById(R.id.txt_penalty_coins);
        txt_description = view.findViewById(R.id.txt_penalty_description);
        bn_add = view.findViewById(R.id.bn_penalty_add);
        bn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Penalty p = new Penalty(txt_title.getText().toString(),txt_coins.getText().toString(),txt_description.getText().toString());
                PenaltyServices penaltyServices = new PenaltyServices(getContext());
                penaltyServices.insert(p);
            }
        });




        return view;
    }


}
