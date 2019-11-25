package com.rezdy.repository;

import com.rezdy.domain.Recipe;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecipeRepository {

    private List<Recipe> recipes;

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

}
