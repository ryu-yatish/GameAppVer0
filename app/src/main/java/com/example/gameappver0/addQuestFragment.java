package com.example.gameappver0;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.gameappver0.data.AppDatabase;
import com.example.gameappver0.data.Penalty;
import com.example.gameappver0.data.Quest;
import com.example.gameappver0.HomeFragment.QuestServices;

import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class addQuestFragment extends Fragment  {


    private EditText txt_title, txt_time, txt_description, txt_date, txt_coins;
    private Button add;
    AppDatabase db;

    public addQuestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_quest, container, false);
        txt_coins = view.findViewById(R.id.txt_penalty_coins);
        txt_description = view.findViewById(R.id.txt_penalty_description);
        txt_title = view.findViewById(R.id.txt_penalty_title);
        txt_time = view.findViewById(R.id.txt_time);
        txt_date = view.findViewById(R.id.txt_date);
        add = view.findViewById(R.id.bn_penalty_add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Quest q;
                q= new Quest("Default","12:30","12/2/20","20","hello");
                q.setTitle(txt_title.getText().toString());
                q.coins=txt_coins.getText().toString();
                q.setDate(txt_date.getText().toString());
                q.setDescription(txt_description.getText().toString());
                q.setTime(txt_time.getText().toString());

                QuestServices questServices = new QuestServices(getContext());
                try {
                    questServices.insertquest(q);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new HomeFragment(),null).commit();
            }
        });



        return view;
    }







}