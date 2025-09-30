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
                Arrays.asList("된장", "두부"),
                "1. 두부1/2모 물6컵 된장2스푼을 준비한다.\n" +
                        "2. 냄비에 물 6컵을 넣고 끓인다.\n" +
                        "3. 된장을 먼저 1스푼 물에 풀고 간을 확인하며 싱거울 시 된장을 추가로 넣는다.\n" +
                        "4. 끓이다가 불을 끄기 전 두부를 넣고 잠시 더 끓여준다.\n" +
                        "*  추가로 감자, 양파, 호박, 대파, 청양고추 등을 넣으면 더 맛있습니다.",
                R.drawable.soybeanpaste_soup));
        recipes.add(new Recipe("라면",
                Arrays.asList("라면"),
                "1. 물을 끓인다.\n" +
                        "2. 면을 넣는다.\n" +
                        "3. 분말 스프와 건더기 스프를 넣는다.\n" +
                        "4. 3~4분 정도 취향껏 면을 익힌다.\n" +
                        "6. 취향에 따라 부재료를 추가하거나 반찬을 준비한다.\n" +
                        "*  PC방 라면처럼 먹고싶다면 면을 꺼내고 국물을 더 끓여 보세요.",
                R.drawable.ramen));
        recipes.add(new Recipe("참치마요덮밥",
                Arrays.asList("참치", "밥", "마요네즈", "양파"),
                "1. 참치의 기름을 다 빼낸다.\n" +
                        "2. 프라이팬에 채썰은 양파와 간장(1,5스푼), 다진마늘(0.5스푼)을 넣는다.\n" +
                        "3. 볶는다.\n" +
                        "4. 밥 위에 볶은 양파를 올린다. (스크램블 에그도 있으면 좋습니다.)\n" +
                        "5. 기름을 뺀 참치를 넣고 원하는 만큼 마요네즈를 뿌립니다.\n" +
                        "*  김가루, 단무지를 넣으면 더 맛있습니다.",
                R.drawable.chachi_bop));
        recipes.add(new Recipe("간장계란비빔밥",
                Arrays.asList("계란", "밥", "양파"),
                "1. 양파를 채썬다 (1~2개)\n" +
                        "2. 계란후라이를 만든다.\n" +
                        "3. 양파를 볶는다.\n" +
                        "4. 양파의 숨이 죽으면 간장(2~4큰술), 물(1컵)을 넣고 더 볶는다.\n" +
                        "5. 양파가 졸여지면 불을 끈다.\n" +
                        "6. 밥 위에 볶은 양파와 계란후라이를 올린다.\n" +
                        "*  파슬리 가루 및 통깨를 올려도 됩니다.",
                R.drawable.egg_bop));
        recipes.add(new Recipe("고구마맛탕",
                Arrays.asList("고구마", "물엿", "설탕"),
                "1. 고구마의 껍질을 벗기고 물에 담가놓는다.\n" +
                        "2. 물기를 제거하고 식용유와 고구마를 프라이팬에 살짝 튀겨준다. (중불)\n" +
                        "3. 색이 살짝 진해지면 불을 끄고 뺀다.\n" +
                        "4. 설탕 1, 물 1, 물엿 1/2을 넣고 끓인다.\n" +
                        "5. 고구마를 넣고 졸인다.\n",
                R.drawable.goguma));
        return recipes;
    }
}