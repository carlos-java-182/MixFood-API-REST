package com.mixfood.apirest.projections;

public interface RecipeCardTable
{
    int getId();
    String getName();
    String getThumbRoute();
    long getTotalLikes();
    long getTotalReviews();
    double getAverangeRanking();
    long getViews();
    Category getCategory();

    interface Category
    {
        int getId();
        String getName();
    }

}
