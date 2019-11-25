package com.rezdy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rezdy.domain.Recipe;
import com.rezdy.domain.RecipeContainer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;

import static java.util.Collections.emptyList;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LunchControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldGetRecipesGivenDate() throws Exception {
        LocalDate date = LocalDate.of(2019, 10, 22);

        RecipeContainer recipesExpected = new RecipeContainer();
        recipesExpected.setRecipes(Arrays.asList(
                new Recipe("Ham and Cheese Toastie", Arrays.asList("Ham", "Cheese", "Bread", "Butter")),
                new Recipe("Salad", Arrays.asList("Lettuce", "Tomato", "Cucumber", "Beetroot", "Salad Dressing")),
                new Recipe("Hotdog", Arrays.asList("Hotdog Bun", "Sausage", "Ketchup", "Mustard"))
        ));

        mvc.perform(MockMvcRequestBuilders.get("/").param("date", String.valueOf(date)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(mapper.writeValueAsString(recipesExpected)));
    }

    @Test
    public void shouldGetRecipesWithoutDate() throws Exception {
        RecipeContainer recipesExpected = new RecipeContainer();
        recipesExpected.setRecipes(emptyList());

        mvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string(mapper.writeValueAsString(recipesExpected)));
    }

}
