package com.example.gameappver0;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class CharaterFragment extends Fragment {

    private EditText username;
    private TextView txt_coin;
    private Button bn_save,bn_clear;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String USER = "user";
    public static final String COIN = "coin";

    private String username_load;
    private int coin_load;

    public CharaterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_charater, container, false);
        username = view.findViewById(R.id.txt_username);
        txt_coin = view.findViewById(R.id.txt_coin);

        final Context context = this.getContext();
        updateData(context);
        bn_save = view.findViewById(R.id.bn_save);
        bn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context,"save btn clicked",Toast.LENGTH_SHORT).show();
                saveData(context);
            }
        });

        bn_clear = view.findViewById(R.id.bn_clear);
        bn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defaultData(context);

            }
        });



        return view;
    }

    public void saveData(Context context) {;

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USER, username.getText().toString());
        editor.apply();
        Toast.makeText(context, "username changed to " + username.getText().toString(), Toast.LENGTH_SHORT).show();

    }

    public void loadData(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        username_load = sharedPreferences.getString(USER, "Guest");
        coin_load = sharedPreferences.getInt(COIN,0);
    }

    public void updateData(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        loadData(context);
        username.setText(username_load);
        String s=Integer.toString(coin_load);
        txt_coin.setText(s);

    }

    public void defaultData(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USER, "Guest");
        editor.putInt(COIN,0);
        editor.apply();
        Toast.makeText(context,"default value set",Toast.LENGTH_SHORT);
        updateData(context);
    }
    public void coinIncrease(Context context, int value)
    {
        SharedPreferences sharedPreferences =context.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        int c=sharedPreferences.getInt(COIN,0);
        c=c+value;
        editor.putInt(COIN,c);
        editor.apply();
        updateData(context);
    }
}
