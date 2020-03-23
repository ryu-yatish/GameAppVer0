package com.example.gameappver0.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Penalty {
    @PrimaryKey(autoGenerate = true)
    public int num;

    public Penalty(String title, String coins, String description) {
        this.title = title;
        this.coins = coins;
        this.description = description;
    }

    @ColumnInfo(name = "title")
    public String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @ColumnInfo(name = "coins")
    public String coins;

    @ColumnInfo(name = "description")
    public String description;
}
