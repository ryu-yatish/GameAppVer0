package com.example.gameappver0.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestDao {

    @Query("SELECT * FROM quest")
    List<Quest> getAll();

    @Insert
    void insertAll(Quest... quests);

    @Delete
    void delete(Quest quest);


}
