package com.example.gameappver0.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Quest.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuestDao questDao();

}

