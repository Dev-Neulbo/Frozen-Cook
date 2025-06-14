package com.example.appprogm_lhj;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "diary")
public class Diary {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String photoUri;
    public String comment;
}