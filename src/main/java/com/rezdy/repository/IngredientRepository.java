package com.rezdy.repository;

import com.rezdy.domain.Ingredient;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class IngredientRepository {

    private List<Ingredient> ingredients;

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public Ingredient getIngredientByTitle(String title) {
        return ingredients.stream()
                .filter(ingredient -> title.equals(ingredient.getTitle()))
                .findAny()
                .orElse(null);
    }

    public List<Ingredient> getIngredientsNotExpired(LocalDate date) {
        return ingredients.stream().sorted(Comparator.comparing(Ingredient::getBestBefore).reversed())
                .filter(ingredient -> ingredient.getUseBy().isAfter(date) || ingredient.getUseBy().isEqual(date))
                .collect(Collectors.toList());
    }
}
