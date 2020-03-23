package com.example.gameappver0.data;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Quest {

    public Quest(String title, String time, String date, String coins, String description) {
        this.title = title;
        this.time = time;
        this.date = date;
        this.coins = coins;
        this.description = description;
    }

    @PrimaryKey(autoGenerate = true)
    public int questnum;


    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "time")
    public String time;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "coins")
    public String coins;

    @ColumnInfo(name = "description")
    public String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
