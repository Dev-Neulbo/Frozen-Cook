package com.example.appprogm_lhj;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {
    public String name;
    public List<String> ingredients;
    public String instructions;
    public int imageResId;  // 이미지 리소스 ID

    public Recipe(String name, List<String> ingredients, String instructions, int imageResId) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.imageResId = imageResId;
    }
}
