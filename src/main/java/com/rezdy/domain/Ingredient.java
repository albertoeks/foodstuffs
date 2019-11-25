package com.rezdy.domain;


import java.time.LocalDate;
import java.util.Objects;

public class Ingredient {

    private String title;
    private LocalDate best_before;
    private LocalDate use_by;

    public Ingredient(String title, LocalDate best_before, LocalDate use_by) {
        this.title = title;
        this.best_before = best_before;
        this.use_by = use_by;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getBest_before() {
        return best_before;
    }

    public LocalDate getUse_by() {
        return use_by;
    }

    public boolean isBetweenBestBeforeAndUseBy(LocalDate date) {
        return (date.isEqual(this.best_before) || date.isAfter(this.best_before)) &&
                (date.isBefore(this.use_by) || date.isEqual(this.use_by));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(best_before, that.best_before) &&
                Objects.equals(use_by, that.use_by);
    }
}
