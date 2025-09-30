package com.example.appprogm_lhj;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Ingredient.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract IngredientDao ingredientDao();

}