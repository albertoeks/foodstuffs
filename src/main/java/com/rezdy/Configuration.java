package com.rezdy;

import com.rezdy.domain.Ingredient;
import com.rezdy.domain.Recipe;
import com.rezdy.repository.IngredientRepository;
import com.rezdy.repository.RecipeRepository;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public IngredientRepository ingredientRepository() {
        IngredientRepository ingredientRepository = new IngredientRepository();

        List<Ingredient> ingredients = Arrays.asList( //title, best_before, use_by
                new Ingredient("Ham", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 11)),
                new Ingredient("Cheese", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 11)),
                new Ingredient("Bread", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 11)),
                new Ingredient("Butter", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 11)),
                new Ingredient("Bacon", LocalDate.of(2019, 11, 1), LocalDate.of(2019, 11, 6)),
                new Ingredient("Eggs", LocalDate.of(2019, 11, 1), LocalDate.of(2019, 11, 6)),
                new Ingredient("Mushrooms", LocalDate.of(2019, 10, 22), LocalDate.of(2019, 10, 25)),
                new Ingredient("Sausage", LocalDate.of(2019, 11, 1), LocalDate.of(2019, 11, 6)),
                new Ingredient("Hotdog Bun", LocalDate.of(2019, 10, 22), LocalDate.of(2019, 11, 6)),
                new Ingredient("Ketchup", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 11)),
                new Ingredient("Mustard", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 11)),
                new Ingredient("Lettuce", LocalDate.of(2019, 11, 1), LocalDate.of(2019, 11, 6)),
                new Ingredient("Tomato", LocalDate.of(2019, 11, 1), LocalDate.of(2019, 11, 6)),
                new Ingredient("Cucumber", LocalDate.of(2019, 11, 1), LocalDate.of(2019, 11, 6)),
                new Ingredient("Beetroot", LocalDate.of(2019, 11, 1), LocalDate.of(2019, 11, 6)),
                new Ingredient("Salad Dressing", LocalDate.of(2019, 10, 22), LocalDate.of(2019, 10, 25))
        );

        ingredientRepository.setIngredients(ingredients);
        return ingredientRepository;
    }

    @Bean
    public RecipeRepository recipeRepository() {
        RecipeRepository recipeRepository = new RecipeRepository();

        List<Recipe> recipes = Arrays.asList(
                new Recipe("Ham and Cheese Toastie", Arrays.asList("Ham", "Cheese", "Bread", "Butter")),
                new Recipe("Fry-up", Arrays.asList("Bacon", "Eggs", "Baked Beans", "Mushrooms", "Sausage", "Bread")),
                new Recipe("Salad", Arrays.asList("Lettuce", "Tomato", "Cucumber", "Beetroot", "Salad Dressing")),
                new Recipe("Hotdog", Arrays.asList("Hotdog Bun", "Sausage", "Ketchup", "Mustard")),
                new Recipe("Omelette", Arrays.asList("Eggs", "Mushrooms", "Milk", "Salt", "Pepper", "Spinach"))
        );

        recipeRepository.setRecipes(recipes);
        return recipeRepository;
    }
}
