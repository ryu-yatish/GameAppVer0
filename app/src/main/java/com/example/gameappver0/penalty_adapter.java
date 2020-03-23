package com.example.gameappver0;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gameappver0.data.Penalty;
import com.example.gameappver0.data.Quest;

import java.util.List;

public class penalty_adapter extends RecyclerView.Adapter<penalty_adapter.MyViewHolder>  {

    List<Penalty> penalties;
    Context context;

    public penalty_adapter( Context context, List<Penalty> penalties) {
        this.penalties = penalties;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rec_row_penalty,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(penalties.get(position).title);

    }

    @Override
    public int getItemCount() {
        return penalties.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title,time;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_title_rec);



        }
    }
}
