package com.example.gameappver0;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class PreHomeFragment extends Fragment {


    public PreHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pre_home, container, false);



        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //Second fragment after 5 seconds appears
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new HomeFragment(),null).addToBackStack(null).commit();
            }
        };

        handler.postDelayed(runnable, 2500);

        return view;
    }

}
