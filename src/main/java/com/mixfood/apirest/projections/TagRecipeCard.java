package com.mixfood.apirest.projections;

import com.mixfood.apirest.entity.Recipe;

import java.util.Date;
import java.util.List;

public interface TagRecipeCard
{
     List<Recipe> getRecipes();

     interface Recipe
     {
         int getId();
         int getAverangeRanking();
         int getViews();
         int getTotalLikes();
         String getName();
         String getThumbRoute();
         Date getCreateAt();
         Category getCategory();
         User getUser();
     }

    interface User{
        int getId();
        String getName();
        String getLastname();
    }

    interface  Category{
        int getId();
        String getName();
    }

}
