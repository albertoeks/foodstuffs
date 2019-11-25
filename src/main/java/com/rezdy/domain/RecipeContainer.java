package com.rezdy.domain;


import java.util.List;
import java.util.Objects;

public class RecipeContainer {

    private List<Recipe> recipes;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeContainer that = (RecipeContainer) o;
        return Objects.equals(recipes, that.recipes);
    }

}
