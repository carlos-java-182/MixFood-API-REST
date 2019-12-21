package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Recipe;
import com.mixfood.apirest.projections.RecipeCard;
import com.mixfood.apirest.projections.RecipeSearch;

import java.util.List;

public interface RecipeService
{
    public List<Recipe> findAll();
    public Recipe findById(int id);
    public Recipe save(Recipe recipe);
    public void delete(int id);
    public List<RecipeCard> findAllForCards();
    public List<RecipeSearch> findLikeName(String term);

}
