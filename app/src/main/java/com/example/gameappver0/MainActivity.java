package com.example.gameappver0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static FragmentManager fragmentManager ;
    public static int BScoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        if(findViewById(R.id.fragment_container)!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }

            HomeFragment homeFragment= new HomeFragment();

            fragmentManager.beginTransaction().add(R.id.fragment_container,homeFragment,null).addToBackStack(null).commit();

        }
    }







}
