package com.example.appprogm_lhj;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        ImageView recipeImage = findViewById(R.id.recipeImage);
        int imageResId = getIntent().getIntExtra("recipe_image_res_id", -1);
        if (imageResId != -1) {
            recipeImage.setImageResource(imageResId);
        }

        // 툴바 설정
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("레시피 상세");
        }

        toolbar.setNavigationOnClickListener(v -> finish());

        TextView nameView = findViewById(R.id.recipeName);
        TextView ingredientsView = findViewById(R.id.recipeIngredients);
        TextView instructionsView = findViewById(R.id.recipeInstructions);

        String name = getIntent().getStringExtra("recipe_name");
        ArrayList<String> ingredients = getIntent().getStringArrayListExtra("recipe_ingredients");
        String instructions = getIntent().getStringExtra("recipe_instructions");

        nameView.setText(name);
        ingredientsView.setText(TextUtils.join(", ", ingredients));
        instructionsView.setText(instructions);
    }
}