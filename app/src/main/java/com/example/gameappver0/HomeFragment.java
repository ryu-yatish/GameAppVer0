package com.example.gameappver0;


import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.gameappver0.data.AppDatabase;
import com.example.gameappver0.data.Penalty;
import com.example.gameappver0.data.PenaltyDatabase;
import com.example.gameappver0.data.Quest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener, rec_viewAdapter.OnNoteListener {

    private FloatingActionButton add_quest,add_penalty;
    private ImageButton bn_character;
    private TextView username,txt_coins;
    public static final String SHARED_PREFS="sharedPrefs";
    public static final String USER = "user";
    public static final String COIN = "coin";
    private String username_load;
    private int coin_load;
    List<Quest> q1;
    List<Penalty> p1;


    AppDatabase db;


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
        recyclerView_penalty = view.findViewById(R.id.recyclerView_penalty);

        add_quest = view.findViewById(R.id.add_quest);
        add_quest.setOnClickListener(this);
        add_penalty = view.findViewById(R.id.add_penalty);
        add_penalty.setOnClickListener(this);
        bn_character = view.findViewById(R.id.bn_character);
        bn_character.setOnClickListener(this);
        username = view.findViewById(R.id.txt_username);
        txt_coins = view.findViewById(R.id.txt_home_coins);
        final Context context = this.getContext();
        updateData(context);


        QuestServices questServices = new QuestServices(context);
        try {
            q1 = questServices.getAllQuests();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        PenaltyServices penaltyServices = new PenaltyServices(context);

        try {
            p1 = penaltyServices.getAllpenalties();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        penalty_adapter p_adapter = new penalty_adapter(context,p1);
        recyclerView_penalty.setAdapter(p_adapter);
        recyclerView_penalty.setLayoutManager(new LinearLayoutManager(this.getContext()));

        rec_viewAdapter adapter = new rec_viewAdapter(context,q1,this);
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
        coin_load = sharedPreferences.getInt(COIN, 0);

    }
    public void updateData(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        loadData(context);
        username.setText(username_load);
        txt_coins.setText(Integer.toString(coin_load));

    }

    @Override
    public void onNoteClick(int position) {
        Log.d(null,"onNoteListener clicked");
        Fragment questDetailsFragment = new QuestDetailsFragment();
        Bundle args = new Bundle();

        args.putInt("position",position);
        questDetailsFragment.setArguments(args);

        MainActivity.fragmentManager.beginTransaction().replace(R.id.fragment_container,questDetailsFragment,null).addToBackStack(null).commit();

    }

    public static class QuestServices {

        private AppDatabase db;
        public QuestServices(Context context) {
            db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Quest").build();
        }
        public List<Quest> getAllQuests() throws ExecutionException, InterruptedException {
            return new GetQuestsAsyncTask().execute().get();
        }

        private class GetQuestsAsyncTask extends AsyncTask<Void,Void,List<Quest>>
        {

            @Override
            protected List<Quest> doInBackground(Void... voids) {
                return db.questDao().getAll();
            }
        }



        public void insertquest(Quest q ) throws ExecutionException , InterruptedException {
            new insertQuestAsyncTask().execute(q);
            return ;
        }

        private class insertQuestAsyncTask extends AsyncTask<Quest,Void,Void>
        {

            @Override
            protected Void doInBackground(Quest... quests) {
                db.questDao().insertAll(quests[0]);
                return null;
            }
        }

        public void deletequest(Quest q ) throws ExecutionException , InterruptedException {
            new deleteQuestAsyncTask().execute(q);
            return ;
        }

        private class deleteQuestAsyncTask extends AsyncTask<Quest,Void,Void>
        {

            @Override
            protected Void doInBackground(Quest... quests) {
                db.questDao().delete(quests[0]);
                return null;
            }
        }
    }

    public static class PenaltyServices{


        private PenaltyDatabase pdb ;
        public PenaltyServices(Context ct) {
            pdb = Room.databaseBuilder(ct,PenaltyDatabase.class,"Penalty").build();
        }

        public List<Penalty> getAllpenalties() throws ExecutionException, InterruptedException {
            return new getAllAsyncTask().execute().get();
        }

        private class getAllAsyncTask extends AsyncTask<Void,Void,List<Penalty> >
        {

            @Override
            protected List<Penalty> doInBackground(Void... voids) {

                return pdb.penaltyDao().getAllpenalties();
            }
        }

        public void insert(Penalty p) {
            new insertPenaltyAsyncTask().execute(p);
            return ;
        }

        private class insertPenaltyAsyncTask extends AsyncTask<Penalty, Void, Void >{


            @Override
            protected Void doInBackground(Penalty... penalties) {
                pdb.penaltyDao().insertAll(penalties[0]);
                return null;
            }
        }

        public void delete(Penalty p) {
            new deletePenaltyAsyncTask().execute(p);
            return;
        }
        private class deletePenaltyAsyncTask extends AsyncTask<Penalty,Void,Void> {

            @Override
            protected Void doInBackground(Penalty... penalties) {
                pdb.penaltyDao().delete(penalties[0]);
                return null;
            }
        }

    }


}
