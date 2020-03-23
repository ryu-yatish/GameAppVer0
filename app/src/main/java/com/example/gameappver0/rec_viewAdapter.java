package com.example.gameappver0;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gameappver0.data.Quest;

import java.util.List;

public class rec_viewAdapter extends RecyclerView.Adapter<rec_viewAdapter.MyViewHolder> {

    List<Quest> Quests;
    Context context;
    private OnNoteListener mOnNoteListener;

    public rec_viewAdapter(Context ct, List<Quest> quests, OnNoteListener onNoteListener){
        Quests=quests;
        context = ct;
        mOnNoteListener = onNoteListener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rec_row_quest,parent,false);


        return new MyViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(Quests.get(position).title);
        holder.time.setText(Quests.get(position).time);
    }

    @Override
    public int getItemCount() {
        return Quests.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title,time;
        OnNoteListener onNoteListener;
        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_title_rec);
            time = itemView.findViewById(R.id.txt_time_rec);
            this.onNoteListener = onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
