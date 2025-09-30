package com.example.appprogm_lhj;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;
    private List<Ingredient> ingredients;
    private IngredientAdapter adapter;
    private RecyclerView recyclerView;

    private EditText editName, editQuantity, editExpiry;
    private Button btnAdd, btnRecommend;
    private int editingPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(
                        getApplicationContext(),
                        AppDatabase.class,
                        "ingredient-database")
                .allowMainThreadQueries()
                .build();

        recyclerView = findViewById(R.id.ingredientRecyclerView);
        editName = findViewById(R.id.editName);
        editQuantity = findViewById(R.id.editQuantity);
        editExpiry = findViewById(R.id.editExpiry);
        btnAdd = findViewById(R.id.btnAdd);
        btnRecommend = findViewById(R.id.btnRecommend);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ingredients = db.ingredientDao().getAllIngredients();
        adapter = new IngredientAdapter(ingredients);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemLongClickListener((ingredient, position) -> {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("삭제 확인")
                    .setMessage(ingredient.name + " 을(를) 삭제하시겠습니까?")
                    .setPositiveButton("삭제", (dialog, which) -> {
                        db.ingredientDao().delete(ingredient);
                        ingredients.remove(position);
                        adapter.notifyItemRemoved(position);
                        Toast.makeText(MainActivity.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();

                        editingPosition = -1;
                        btnAdd.setText("재료 추가");
                        clearInputs();
                    })
                    .setNegativeButton("취소", null)
                    .show();
        });

        adapter.setOnItemClickListener((ingredient, position) -> {
            editName.setText(ingredient.name);
            editQuantity.setText(String.valueOf(ingredient.quantity));
            editExpiry.setText(ingredient.expiryDate);
            editingPosition = position;
            btnAdd.setText("수정 완료");
        });

        btnAdd.setOnClickListener(v -> {
            String name = editName.getText().toString().trim();
            String quantityStr = editQuantity.getText().toString().trim();
            String expiry = editExpiry.getText().toString().trim();

            if (name.isEmpty() || quantityStr.isEmpty() || expiry.isEmpty()) {
                Toast.makeText(this, "모든 항목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            int quantity;
            try {
                quantity = Integer.parseInt(quantityStr);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "수량은 숫자로 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (editingPosition == -1) {
                Ingredient newIngredient = new Ingredient(name, quantity, expiry);
                db.ingredientDao().insert(newIngredient);
                Toast.makeText(this, "재료가 추가되었습니다.", Toast.LENGTH_SHORT).show();
            } else {
                Ingredient ingredientToUpdate = ingredients.get(editingPosition);
                ingredientToUpdate.name = name;
                ingredientToUpdate.quantity = quantity;
                ingredientToUpdate.expiryDate = expiry;
                db.ingredientDao().update(ingredientToUpdate);
                Toast.makeText(this, "재료가 수정되었습니다.", Toast.LENGTH_SHORT).show();

                editingPosition = -1;
                btnAdd.setText("재료 추가");
            }

            ingredients.clear();
            ingredients.addAll(db.ingredientDao().getAllIngredients());
            adapter.notifyDataSetChanged();

            clearInputs();
        });

        btnRecommend.setOnClickListener(v -> {
            List<Recipe> allRecipes = RecipeRepository.getPredefinedRecipes();

            Set<String> ownedNames = new HashSet<>();
            for (Ingredient ing : db.ingredientDao().getAllIngredients()) {
                ownedNames.add(ing.name);
            }

            List<String> availableRecipes = new ArrayList<>();
            for (Recipe recipe : allRecipes) {
                if (ownedNames.containsAll(recipe.ingredients)) {
                    availableRecipes.add(recipe.name);
                }
            }

            if (availableRecipes.isEmpty()) {
                new AlertDialog.Builder(this)
                        .setTitle("레시피 추천")
                        .setMessage("사용 가능한 레시피가 없습니다.")
                        .setPositiveButton("확인", null)
                        .show();
            } else {
                String[] recipeNames = availableRecipes.toArray(new String[0]);
                new AlertDialog.Builder(this)
                        .setTitle("추천 레시피")
                        .setItems(recipeNames, (dialog, which) -> {
                            String selectedRecipeName = recipeNames[which];
                            openRecipeDetail(selectedRecipeName);
                        })
                        .setNegativeButton("취소", null)
                        .show();
            }
        });
    }

    private void openRecipeDetail(String recipeName) {
        Recipe selectedRecipe = null;
        for (Recipe recipe : RecipeRepository.getPredefinedRecipes()) {
            if (recipe.name.equals(recipeName)) {
                selectedRecipe = recipe;
                break;
            }
        }
        if (selectedRecipe != null) {
            Intent intent = new Intent(this, RecipeDetailActivity.class);
            intent.putExtra("recipe_name", selectedRecipe.name);
            intent.putStringArrayListExtra("recipe_ingredients", new ArrayList<>(selectedRecipe.ingredients));
            intent.putExtra("recipe_instructions", selectedRecipe.instructions);
            intent.putExtra("recipe_image_res_id", selectedRecipe.imageResId);
            startActivity(intent);
        }
    }

    private void clearInputs() {
        editName.setText("");
        editQuantity.setText("");
        editExpiry.setText("");
    }
}