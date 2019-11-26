package com.rezdy.domain;


import java.time.LocalDate;
import java.util.Objects;

public class Ingredient {

    private String title;
    private LocalDate bestBefore;
    private LocalDate useBy;

    public Ingredient(String title, LocalDate bestBefore, LocalDate useBy) {
        this.title = title;
        this.bestBefore = bestBefore;
        this.useBy = useBy;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public LocalDate getUseBy() {
        return useBy;
    }

    public boolean isBetweenBestBeforeAndUseBy(LocalDate date) {
        return (date.isEqual(this.bestBefore) || date.isAfter(this.bestBefore)) &&
                (date.isBefore(this.useBy) || date.isEqual(this.useBy));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(bestBefore, that.bestBefore) &&
                Objects.equals(useBy, that.useBy);
    }
}
