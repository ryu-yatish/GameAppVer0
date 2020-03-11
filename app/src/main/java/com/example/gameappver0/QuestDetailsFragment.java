package com.example.gameappver0;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestDetailsFragment extends Fragment {

    TextView txt_title;
    String s1[];
    public QuestDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quest_details, container, false);
        txt_title = view.findViewById(R.id.txt_quest_details_title);
        s1 = getResources().getStringArray(R.array.list1);
        int pos = getArguments().getInt("position");
        txt_title.setText(s1[pos]);

        return view;
    }

}
