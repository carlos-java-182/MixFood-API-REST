package com.mixfood.apirest.projections;

import com.mixfood.apirest.entity.Image;
import com.mixfood.apirest.entity.RecipeIngredient;

import java.util.List;

public interface RecipeEdit
{
    int getId();
    String getName();
    String getPreparationTime();
    String getDescription();
    String getVideoFrame();
    String getPreparationSteps();
    String getStatus();
    String getDifficulty();
    CategoryId getCategory();
    List<TagId> getTags();
    List<RecipeIngredient> getRecipeIngredients();
    List<RecipeProfile.Image> getImages();
}
