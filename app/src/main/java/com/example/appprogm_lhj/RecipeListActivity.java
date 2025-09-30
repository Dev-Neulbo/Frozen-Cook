package com.example.appprogm_lhj;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecipeListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeListAdapter adapter;
    private List<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        recyclerView = findViewById(R.id.recipeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        recipeList = RecipeRepository.getPredefinedRecipes();


        adapter = new RecipeListAdapter(recipeList, recipe -> {

            Intent intent = new Intent(RecipeListActivity.this, RecipeDetailActivity.class);
            intent.putExtra("recipe_name", recipe.name);
            intent.putExtra("recipe_ingredients", new ArrayList<>(recipe.ingredients));
            intent.putExtra("recipe_instructions", recipe.instructions);
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
    }
}