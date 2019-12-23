package com.mixfood.apirest.projections;

public interface RecipeLatest
{
    int getId();
    String getName();
    int getAverangeRanking();
    String getThumbRoute();
    Category getCategory();
    User getUser();

    interface Category{
        int getId();
        String getName();
    }

    interface  User{
        int getId();
        String getName();
        String getLastname();
    }
}
