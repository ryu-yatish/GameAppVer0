package com.example.gameappver0;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.gameappver0.data.AppDatabase;
import com.example.gameappver0.data.Quest;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements rec_viewAdapter.OnNoteListener {
    public static FragmentManager fragmentManager ;
    public static int BScoin;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String USER = "user";
    public static final String COIN = "coin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        if(findViewById(R.id.fragment_container)!=null) {
            if (savedInstanceState != null) {
                return;
            }

            Fragment PrehomeFragment = new PreHomeFragment();

            fragmentManager.beginTransaction().add(R.id.fragment_container, PrehomeFragment, null).commit();
        }


    }


    @Override
    public void onNoteClick(int position) {

    }


}
