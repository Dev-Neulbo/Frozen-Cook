package com.example.appprogm_lhj;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Ingredient.class, Diary.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract IngredientDao ingredientDao();
    public abstract DiaryDao diaryDao();
}