package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.RecipeIngredient;

import java.util.List;

public interface RecipeIngredientService
{
    public List<RecipeIngredient> findAll();
    public RecipeIngredient findById(int id);
    public RecipeIngredient save(RecipeIngredient recipeIngredient);
    public void delete(int id);
}
