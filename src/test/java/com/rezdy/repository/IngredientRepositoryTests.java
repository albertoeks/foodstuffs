package com.rezdy.repository;

import com.rezdy.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class IngredientRepositoryTests {

    private IngredientRepository ingredientRepository;

    @Before
    public void setUp() {
        ingredientRepository = new IngredientRepository();
    }

    @Test
    public void shouldGetIngredientByTitle() {
        String title = "Ham";

        ingredientRepository.setIngredients(Arrays.asList(
                new Ingredient("Ham", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 11)),
                new Ingredient("Cheese", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 11)),
                new Ingredient("Bread", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 11)),
                new Ingredient("Butter", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 11))
        ));

        Ingredient expectedIngredient = new Ingredient(
                "Ham",
                LocalDate.of(2019, 11, 6),
                LocalDate.of(2019, 11, 11)
        );

        assertEquals(ingredientRepository.getIngredientByTitle(title), expectedIngredient);
    }

    @Test
    public void shouldNotFindIngredientByTitle() {
        String title = "another-ingredient";

        ingredientRepository.setIngredients(Arrays.asList(
                new Ingredient("Ham", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 11)),
                new Ingredient("Cheese", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 11))
        ));

        assertNull(ingredientRepository.getIngredientByTitle(title));
    }

    @Test
    public void shouldGetIngredientsWithUseByAfterDate() {
        LocalDate date = LocalDate.of(2019, 11, 11);

        ingredientRepository.setIngredients(Arrays.asList(
                new Ingredient("Ham", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 11)),
                new Ingredient("Cheese", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 10)),
                new Ingredient("Bread", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 12))
        ));

        List<Ingredient> expectedIngredients = Arrays.asList(
                new Ingredient("Ham", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 11)),
                new Ingredient("Bread", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 12))
        );

        assertEquals(ingredientRepository.getIngredientsNotExpired(date), expectedIngredients);
    }

    @Test
    public void shouldNotFindIngredientsWithUseByAfterDate() {
        LocalDate date = LocalDate.of(2019, 11, 13);

        ingredientRepository.setIngredients(Arrays.asList(
                new Ingredient("Ham", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 11)),
                new Ingredient("Cheese", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 10)),
                new Ingredient("Bread", LocalDate.of(2019, 11, 6), LocalDate.of(2019, 11, 12))
        ));

        assertEquals(ingredientRepository.getIngredientsNotExpired(date), emptyList());
    }
}
