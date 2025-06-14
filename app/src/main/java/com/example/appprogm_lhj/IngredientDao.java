package com.example.appprogm_lhj;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appprogm_lhj.Ingredient;

import java.util.List;

@Dao
public interface IngredientDao {

    @Insert
    void insert(Ingredient ingredient);

    @Update
    void update(Ingredient ingredient);

    @Delete
    void delete(Ingredient ingredient);

    @Query("SELECT * FROM Ingredient")
    List<Ingredient> getAllIngredients();

    @Query("SELECT * FROM Ingredient WHERE id = :id")
    Ingredient getIngredientById(int id);
}
