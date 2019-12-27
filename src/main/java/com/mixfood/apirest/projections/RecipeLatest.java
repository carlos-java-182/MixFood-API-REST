package com.mixfood.apirest.projections;

public interface RecipeLatest
{
    int getId();
    int getAverangeRanking();
    String getName();
    String getDescription();
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
