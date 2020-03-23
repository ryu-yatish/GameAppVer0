package com.example.gameappver0;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gameappver0.data.AppDatabase;
import com.example.gameappver0.data.Quest;
import com.example.gameappver0.HomeFragment.QuestServices;

import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestDetailsFragment extends Fragment implements View.OnClickListener {

    TextView txt_title,txt_time,txt_date,txt_description,txt_coins;
    Button bn_finish,bn_delete;
    List<Quest> s1;
    AppDatabase db;
    int pos;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String USER = "user";
    public static final String COIN = "coin";
    public QuestDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        QuestServices questServices = new QuestServices(getContext());
        try {
            s1 = questServices.getAllQuests();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        View view = inflater.inflate(R.layout.fragment_quest_details, container, false);
        txt_title = view.findViewById(R.id.txt_quest_details_title);
        txt_coins = view.findViewById(R.id.txt_quest_details_coins);
        txt_date =view.findViewById(R.id.txt_quest_details_date);
        txt_description = view.findViewById(R.id.txt_quest_details_description);
        txt_time = view.findViewById(R.id.txt_quest_details_time);
        bn_finish = view.findViewById(R.id.bn_details_finish);
        bn_delete = view.findViewById(R.id.bn_details_delete);
        pos = getArguments().getInt("position");

        bn_delete.setOnClickListener(this);
        bn_finish.setOnClickListener(this);

        txt_title.setText(s1.get(pos).title);
        txt_description.setText(s1.get(pos).description);
        txt_time.setText(s1.get(pos).time);
        txt_date.setText(s1.get(pos).date);
        txt_coins.setText(s1.get(pos).coins);




        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bn_details_finish:
                int c =0;
                c = Integer.parseInt(s1.get(pos).coins);
                coinIncrease(getContext(),c);
                Toast.makeText(getContext(),"quest finished, "+c+" coins added. quests remain until you delete them",Toast.LENGTH_LONG);
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new HomeFragment(),null).commit();
                break;

            case R.id.bn_details_delete:
                QuestServices questServices = new QuestServices(getContext());
                try {
                    questServices.deletequest(s1.get(pos));
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,new HomeFragment(),null).commit();

                break;

        }
    }

    public void coinIncrease(Context context, int value)
    {
        SharedPreferences sharedPreferences =context.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int c=sharedPreferences.getInt(COIN,0);
        c=c+value;
        editor.putInt(COIN,c);
        editor.apply();

    }
}
