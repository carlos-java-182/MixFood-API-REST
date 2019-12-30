package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.RecipeIngredient;
import com.mixfood.apirest.models.dao.RecipeIngredientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeIngredientServiceImpl implements RecipeIngredientService
{
    @Autowired
    private RecipeIngredientDAO recipeIngredientDAO;

    @Override
    public List<RecipeIngredient> findAll() {
        return (List<RecipeIngredient>) recipeIngredientDAO.findAll();
    }

    @Override
    public RecipeIngredient findById(int id) {
        return recipeIngredientDAO.findById(id).orElse(null);
    }

    @Override
    public RecipeIngredient save(RecipeIngredient recipeIngredient) {
        return recipeIngredientDAO.save(recipeIngredient);
    }

    @Override
    public void delete(int id) {
        recipeIngredientDAO.deleteById(id);
    }
}
