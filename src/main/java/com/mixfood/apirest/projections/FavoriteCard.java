package com.mixfood.apirest.projections;

public interface FavoriteCard
{
    int getId();
    Recipe getRecipe();
    UserName getUser();

    interface Recipe
    {
        int getId();
        double getAverangeRanking();
        long getViews();
        long getTotalLikes();
        String getName();
        String getThumbRoute();
        Category getCategory();
    }

    interface Category
    {
        int getId();
        String getName();
    }
}
