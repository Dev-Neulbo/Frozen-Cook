package com.example.appprogm_lhj;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Ingredient {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public int quantity;
    public String expiryDate;

    public Ingredient(String name, int quantity, String expiryDate) {
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }
}