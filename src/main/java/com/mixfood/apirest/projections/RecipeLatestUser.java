package com.mixfood.apirest.projections;

public interface RecipeLatestUser
{
    int getId();
    int getAverangeRanking();
    String getName();
    String getDescription();
    String getThumbRoute();

    RecipeLatest.Category getCategory();
    interface Category
    {
        int getId();
        String getName();
    }
}
