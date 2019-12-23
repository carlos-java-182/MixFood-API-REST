package com.mixfood.apirest.projections;

public interface RecipeLatest
{
    int getId();
    String getName();
    int getAverangeRanking();
    String getThumbRoute();
    Category getCategory();

    interface Category{
        int getId();
        String getName();
    }
}
