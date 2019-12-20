package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Recipe;

import java.util.List;

public interface RecipeService
{
    public List<Recipe> findAll();
    public Recipe findById(int id);
    public Recipe save(Recipe recipe);
    public void delete(int id);
    public List<Object> findAllForCards();

}
