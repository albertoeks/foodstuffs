package com.rezdy.service;

import com.rezdy.domain.Ingredient;
import com.rezdy.domain.Recipe;
import com.rezdy.domain.RecipeContainer;
import com.rezdy.repository.IngredientRepository;
import com.rezdy.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class LunchServiceTests {

    private LunchService lunchService;

    private IngredientRepository ingredientRepository;
    private RecipeRepository recipeRepository;

    @Before
    public void setUp() {
        ingredientRepository = new IngredientRepository();
        recipeRepository = new RecipeRepository();
        lunchService = new LunchService(ingredientRepository, recipeRepository);
    }

    @Test
    public void shouldGetRecipesWithIngredientsAvailable() {
        LocalDate date = LocalDate.of(2019, 11, 22);

        recipeRepository.setRecipes(Arrays.asList(
                new Recipe("Ham and Cheese Toastie", Arrays.asList("Ham", "Cheese", "Bread", "Butter")),
                new Recipe("Fry-up", Arrays.asList("Bacon", "Eggs", "Baked Beans", "Mushrooms", "Sausage", "Bread")) //some ingredients unavailable
        ));

        ingredientRepository.setIngredients(Arrays.asList(
                new Ingredient("Ham", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 22)),
                new Ingredient("Cheese", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 23)),
                new Ingredient("Bread", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 24)),
                new Ingredient("Butter", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 25)),
                new Ingredient("Bacon", LocalDate.of(2019, 11, 11), LocalDate.of(2019, 11, 20)),
                new Ingredient("Eggs", LocalDate.of(2019, 11, 11), LocalDate.of(2019, 11, 20))
        ));

        RecipeContainer expectedRecipes = new RecipeContainer();
        expectedRecipes.setRecipes(Arrays.asList(
                new Recipe("Ham and Cheese Toastie", Arrays.asList("Ham", "Cheese", "Bread", "Butter"))
        ));

        assertEquals(lunchService.getRecipes(date), expectedRecipes);
    }

    @Test
    public void shouldGetRecipesWithIngredientsNotExpired() {
        LocalDate date = LocalDate.of(2019, 11, 22);

        recipeRepository.setRecipes(Arrays.asList(
                new Recipe("Ham and Cheese Toastie", Arrays.asList("Ham", "Cheese", "Bread", "Butter")),
                new Recipe("Fry-up", Arrays.asList("Bacon", "Eggs", "Baked Beans", "Mushrooms", "Sausage", "Bread"))
        ));

        ingredientRepository.setIngredients(Arrays.asList(
                new Ingredient("Ham", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 22)),
                new Ingredient("Cheese", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 23)),
                new Ingredient("Bread", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 24)),
                new Ingredient("Butter", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 25)),
                new Ingredient("Bacon", LocalDate.of(2019, 11, 1), LocalDate.of(2019, 11, 6)), //expired
                new Ingredient("Eggs", LocalDate.of(2019, 11, 1), LocalDate.of(2019, 11, 22)),
                new Ingredient("Mushrooms", LocalDate.of(2019, 10, 22), LocalDate.of(2019, 10, 23)),
                new Ingredient("Sausage", LocalDate.of(2019, 11, 1), LocalDate.of(2019, 11, 24)),
                new Ingredient("Hotdog Bun", LocalDate.of(2019, 10, 22), LocalDate.of(2019, 11, 25)),
                new Ingredient("Baked Beans", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 26)),
                new Ingredient("Bread", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 27))
        ));

        RecipeContainer expectedRecipes = new RecipeContainer();
        expectedRecipes.setRecipes(Arrays.asList(
                new Recipe("Ham and Cheese Toastie", Arrays.asList("Ham", "Cheese", "Bread", "Butter"))
        ));

        assertEquals(lunchService.getRecipes(date), expectedRecipes);
    }

    @Test
    public void shouldNotFindAnyRecipesWithIngredientsNotExpired() {
        LocalDate date = LocalDate.of(2019, 11, 11);

        recipeRepository.setRecipes(Arrays.asList(
                new Recipe("Ham and Cheese Toastie", Arrays.asList("Ham", "Cheese", "Bread", "Butter")),
                new Recipe("Fry-up", Arrays.asList("Bacon", "Eggs", "Baked Beans", "Mushrooms", "Sausage", "Bread"))
        ));

        ingredientRepository.setIngredients(Arrays.asList(
                new Ingredient("Ham", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 10)),
                new Ingredient("Cheese", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 10)),
                new Ingredient("Bread", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 10)),
                new Ingredient("Butter", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 10)),
                new Ingredient("Bacon", LocalDate.of(2019, 11, 1), LocalDate.of(2019, 11, 10)),
                new Ingredient("Eggs", LocalDate.of(2019, 11, 1), LocalDate.of(2019, 11, 10)),
                new Ingredient("Mushrooms", LocalDate.of(2019, 10, 22), LocalDate.of(2019, 10, 10)),
                new Ingredient("Sausage", LocalDate.of(2019, 11, 1), LocalDate.of(2019, 11, 10)),
                new Ingredient("Hotdog Bun", LocalDate.of(2019, 10, 22), LocalDate.of(2019, 11, 10)),
                new Ingredient("Baked Beans", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 10)),
                new Ingredient("Bread", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 10))
        ));

        assertEquals(lunchService.getRecipes(date).getRecipes(), emptyList());
    }

    @Test
    public void shouldGetRecipesOrderedByQuantityOfIngredientsBetweenBestBeforeAndUseBy() {
        LocalDate date = LocalDate.of(2019, 11, 10);

        recipeRepository.setRecipes(Arrays.asList(
                new Recipe("Ham and Cheese Toastie", Arrays.asList("Ham", "Cheese", "Bread", "Butter")), // 3 ingredients between BestBefore and UseBy
                new Recipe("Fry-up", Arrays.asList("Bacon", "Eggs", "Baked Beans", "Mushrooms", "Sausage", "Bread")), // 2 ingredients between BestBefore and UseBy
                new Recipe("Salad", Arrays.asList("Lettuce", "Tomato", "Cucumber", "Beetroot", "Salad Dressing")), // 1 ingredient between BestBefore and UseBy
                new Recipe("Hotdog", Arrays.asList("Hotdog Bun", "Sausage", "Ketchup", "Mustard")) // 0 ingredient between BestBefore and UseBy
        ));

        ingredientRepository.setIngredients(Arrays.asList(
                new Ingredient("Ham", LocalDate.of(2019, 11, 8), LocalDate.of(2019, 11, 15)),
                new Ingredient("Cheese", LocalDate.of(2019, 11, 10), LocalDate.of(2019, 11, 13)),
                new Ingredient("Bread", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 10)),
                new Ingredient("Butter", LocalDate.of(2019, 11, 11), LocalDate.of(2019, 11, 20)),
                new Ingredient("Bacon", LocalDate.of(2019, 11, 11), LocalDate.of(2019, 11, 20)),
                new Ingredient("Eggs", LocalDate.of(2019, 11, 11), LocalDate.of(2019, 11, 20)),
                new Ingredient("Baked Beans", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 10)),
                new Ingredient("Mushrooms", LocalDate.of(2019, 11, 11), LocalDate.of(2019, 11, 20)),
                new Ingredient("Sausage", LocalDate.of(2019, 11, 11), LocalDate.of(2019, 11, 20)),
                new Ingredient("Bread", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 10)),
                new Ingredient("Lettuce", LocalDate.of(2019, 11, 11), LocalDate.of(2019, 11, 20)),
                new Ingredient("Tomato", LocalDate.of(2019, 11, 11), LocalDate.of(2019, 11, 20)),
                new Ingredient("Cucumber", LocalDate.of(2019, 11, 11), LocalDate.of(2019, 11, 20)),
                new Ingredient("Beetroot", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 10)),
                new Ingredient("Salad Dressing", LocalDate.of(2019, 11, 11), LocalDate.of(2019, 11, 20)),
                new Ingredient("Sausage", LocalDate.of(2019, 11, 8), LocalDate.of(2019, 11, 15)),
                new Ingredient("Hotdog Bun", LocalDate.of(2019, 11, 11), LocalDate.of(2019, 11, 18)),
                new Ingredient("Ketchup", LocalDate.of(2019, 11, 12), LocalDate.of(2019, 11, 19)),
                new Ingredient("Mustard", LocalDate.of(2019, 11, 13), LocalDate.of(2019, 11, 20))
        ));

        RecipeContainer expectedRecipes = new RecipeContainer();
        expectedRecipes.setRecipes(Arrays.asList(
                new Recipe("Hotdog", Arrays.asList("Hotdog Bun", "Sausage", "Ketchup", "Mustard")),
                new Recipe("Salad", Arrays.asList("Lettuce", "Tomato", "Cucumber", "Beetroot", "Salad Dressing")),
                new Recipe("Fry-up", Arrays.asList("Bacon", "Eggs", "Baked Beans", "Mushrooms", "Sausage", "Bread")),
                new Recipe("Ham and Cheese Toastie", Arrays.asList("Ham", "Cheese", "Bread", "Butter"))
        ));

        assertEquals(lunchService.getRecipes(date), expectedRecipes);
    }

}
