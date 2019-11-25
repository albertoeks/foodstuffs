package com.rezdy.domain;


import java.util.List;

public class Recipe {

    private String title;
    private List<String> ingredients;

    public Recipe(String title, List<String> ingredients) {
        this.title = title;
        this.ingredients = ingredients;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return title.equals(recipe.title) &&
                ingredients.equals(recipe.ingredients);
    }
}
