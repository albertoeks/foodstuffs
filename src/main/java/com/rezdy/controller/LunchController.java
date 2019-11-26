package com.rezdy.controller;

import com.rezdy.domain.RecipeContainer;
import com.rezdy.service.LunchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(produces = APPLICATION_JSON_UTF8_VALUE)
public class LunchController {

    private LunchService lunchService;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

    public LunchController(LunchService lunchService) {
        this.lunchService = lunchService;
    }

    @GetMapping("/lunch")
    public RecipeContainer getLunch(@RequestParam(required = false) String date) {
        if (date == null) return lunchService.getRecipes(LocalDate.now());
        return lunchService.getRecipes(LocalDate.parse(date, formatter));
    }
}
