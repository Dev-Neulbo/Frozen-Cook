package com.example.appprogm_lhj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeRepository {
    public static List<Recipe> getPredefinedRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("김치볶음밥",
                Arrays.asList("김치", "밥"),
                "1. 김치를 종이컵으로 1컵 가량을 꺼내 적당한 크기로 썬다.\n" +
                        "2. 프라이팬을 달군 후 불을 중불로 맞춘다.\n" +
                        "3. 육류를 넣을 거라면 먼저 넣고 볶다 회색빛이 돌면 \n" +
                        "식용유를 추가로 두르고 적절히 김치를 볶는다. \n" +
                        "(기름은 적당히)\n" +
                        "4. 반쯤 볶아진 김치에 밥을 비롯한 기타 재료를 넣는다.",
                R.drawable.kimchi_rice));
        recipes.add(new Recipe("된장국",
                Arrays.asList("된장", "두부", "물"),
                "1. 두부1/2모 물6컵 된장2스푼을 준비한다.\n" +
                        "2. 냄비에 물 6컵을 넣고 끓인다.\n" +
                        "3. 된장을 먼저 1스푼 물에 풀고 간을 확인하며 싱거울 시 된장을 추가로 넣는다.\n" +
                        "4. 끓이다가 불을 끄기 전 두부를 넣고 잠시 더 끓여준다.\n" +
                        "*  추가로 감자, 양파, 호박, 대파, 청양고추 등을 넣으면 더 맛있습니다.",
                R.drawable.soybeanpaste_soup));
        recipes.add(new Recipe("라면",
                Arrays.asList("라면", "물"),
                "1. 물을 끓인다.\n2. 라면과 스프를 넣는다.\n3. 면이 익을 때까지 끓인다.",
                R.drawable.ramen));
        return recipes;
    }
}
