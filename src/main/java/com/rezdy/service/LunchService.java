package com.rezdy.service;

import com.rezdy.domain.Ingredient;
import com.rezdy.domain.Recipe;
import com.rezdy.domain.RecipeContainer;
import com.rezdy.repository.IngredientRepository;
import com.rezdy.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class LunchService {

    private IngredientRepository ingredientRepository;

    private RecipeRepository recipeRepository;

    public LunchService(IngredientRepository ingredientRepository, RecipeRepository recipeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
    }

    public RecipeContainer getRecipes(LocalDate date) {
        List<Recipe> recipes = new ArrayList<>();
        RecipeContainer result = new RecipeContainer();

        getRecipesWithIngredientsByDateUntilUseBy(date).stream()
                .map(recipe -> {
                            List<Ingredient> ingredients = getIngredientsByRecipe(recipe);
                            return new AbstractMap.SimpleEntry(recipe, ingredients.stream().filter(ingredient -> ingredient.isBetweenBestBeforeAndUseBy(date)).count());
                        }
                ).sorted(Comparator.comparingLong(s -> (long) s.getValue())).forEach(simpleEntry -> recipes.add((Recipe) simpleEntry.getKey()));
        result.setRecipes(recipes);
        return result;
    }

    private List<Recipe> getRecipesWithIngredientsByDateUntilUseBy(LocalDate date) {
        List<String> ingredientsTitle = ingredientRepository.getIngredientsNotExpired(date).stream()
                .map(Ingredient::getTitle)
                .collect(Collectors.toList());

        return recipeRepository.getRecipes().stream()
                .filter(recipe -> ingredientsTitle.containsAll(recipe.getIngredients())).collect(Collectors.toList());
    }

    private List<Ingredient> getIngredientsByRecipe(Recipe recipe) {
        return recipe.getIngredients()
                .stream()
                .map(ingredientTitle -> ingredientRepository.getIngredientByTitle(ingredientTitle))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}