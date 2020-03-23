package com.example.gameappver0.data;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Penalty.class},version = 1,exportSchema = false)
public abstract class PenaltyDatabase extends RoomDatabase {
    public abstract PenaltyDao penaltyDao();
}