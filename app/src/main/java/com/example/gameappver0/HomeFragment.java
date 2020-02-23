package com.example.gameappver0;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    private FloatingActionButton add_quest,add_penalty;
    private ImageButton bn_character;
    private TextView username;
    public static final String SHARED_PREFS="sharedPrefs";
    public static final String USER = "user";
    private String username_load;
    String s1[];

    RecyclerView recyclerView_quest,recyclerView_penalty;


    public HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView_quest = view.findViewById(R.id.recyclerView_quest);
        //recyclerView_penalty = view.findViewById(R.id.recyclerView_penalty);

        add_quest = view.findViewById(R.id.add_quest);
        add_quest.setOnClickListener((View.OnClickListener)this);
        add_penalty = view.findViewById(R.id.add_penalty);
        add_penalty.setOnClickListener((View.OnClickListener)this);
        bn_character = view.findViewById(R.id.bn_character);
        bn_character.setOnClickListener((View.OnClickListener)this);
        username = view.findViewById(R.id.txt_username);
        final Context context = this.getContext();
        updateData(context);

        s1=getResources().getStringArray(R.array.list1);


        rec_viewAdapter adapter = new rec_viewAdapter(context,s1);
        recyclerView_quest.setAdapter(adapter);
        recyclerView_quest.setLayoutManager(new LinearLayoutManager(this.getContext()));


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

            case R.id.bn_character:
                Fragment fragment = new CharaterFragment();
                MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment,null).addToBackStack(null).commit();
                break;
        }
    }
    public void loadData(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        username_load = sharedPreferences.getString(USER, "Guest");
    }
    public void updateData(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        loadData(context);
        username.setText(username_load);
    }
}
