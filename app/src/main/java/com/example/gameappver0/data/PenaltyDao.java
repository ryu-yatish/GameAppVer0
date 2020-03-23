package com.example.gameappver0.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PenaltyDao {
    @Query("SELECT * FROM penalty")
    List<Penalty> getAllpenalties();

    @Insert
    void insertAll(Penalty... penalties);

    @Delete
    void delete(Penalty penalty);

}
