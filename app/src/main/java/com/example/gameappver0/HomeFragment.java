package com.example.gameappver0;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    private FloatingActionButton add_quest,add_penalty;
    public HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        add_quest = view.findViewById(R.id.add_quest);
        add_quest.setOnClickListener((View.OnClickListener)this);
        add_penalty = view.findViewById(R.id.add_penalty);
        add_penalty.setOnClickListener((View.OnClickListener)this);





        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_quest:
                Fragment questFragment = new addQuestFragment();
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,questFragment,null).addToBackStack(null).commit();
                break;

            case R.id.add_penalty:
                Fragment penaltyFragment = new addPenaltyFragment();
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,penaltyFragment,null).addToBackStack(null).commit();
                break;
        }
    }
}
