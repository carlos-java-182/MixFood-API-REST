package com.mixfood.apirest.projections;

import com.mixfood.apirest.entity.Image;
import com.mixfood.apirest.entity.Ranking;

import java.util.Date;
import java.util.List;

public interface RecipeProfile
{
    int getId();
    double getAverangeRanking();
    long getViews();
    long getTotalLikes();
    String getName();
    String getPreparationSteps();
    String getDifficulty();
    String getPreparationTime();
    String getVideoFrame();
    String getDescription();
    String getThumbRoute();
    Date getCreateAt();
    List<Image> getImages();
    User getUser();
    List<TagShort> getTags();
    Category getCategory();
    List<Ranking> getRankings();
    List<Ingredients> getRecipeIngredients();

    interface Category
    {
        int getId();
        String getName();
    }

    interface Image
    {
        int getId();
        String getRouteImage();
    }
    interface User
    {
        int getId();
        String getLastname();
        String getName();
        String getDescription();
    }

    interface Ingredients{
        int getId();
        String getQuantity();
        Ingredient getIngredient();
    }

    interface Ingredient
    {
        String getName();
    }

    interface Ranking
    {
        int getId();
        short getPunctuation();
        String getComment();
        Date getCreateAt();
        UserRanking getUser();
    }

    interface UserRanking
    {
        int getId();
        String getLastname();
        String getName();
    }
}