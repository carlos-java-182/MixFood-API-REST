package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Recipe;
import com.mixfood.apirest.models.dao.RecipeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RecipeServiceImpl implements  RecipeService
{
    @Autowired
    //*Object declaration
    private RecipeDAO recipeDAO;

    @Override
    @Transactional
    public List<Recipe> findAll() {
        return (List<Recipe>) recipeDAO.findAll();
    }

    @Override
    public Recipe findById(int id) {
        return recipeDAO.findById(id).orElse(null);
    }

    @Override
    public Recipe save(Recipe recipe) {
        return recipeDAO.save(recipe);
    }

    @Override
    public void delete(int id) {
        recipeDAO.deleteById(id);
    }

    @Override
    public List<Object> findAllForCards() {
        return recipeDAO.findAllForCards();
    }
}
