package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Recipe;
import com.mixfood.apirest.models.dao.RecipeDAO;
import com.mixfood.apirest.projections.RecipeCard;
import com.mixfood.apirest.projections.RecipeLatest;
import com.mixfood.apirest.projections.RecipeSearch;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecipeServiceImpl implements  RecipeService
{
    @Autowired
    //*Object declaration
    private RecipeDAO recipeDAO;

    @Override
    @Transactional(readOnly = true)
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
    public List<RecipeCard> findAllForCards(Pageable pageable) {
        return recipeDAO.findAllForCards(pageable);
    }

    @Override
    public List<RecipeSearch> findLikeName(String term) {
        return recipeDAO.findLikeName(term);
    }

    @Override
    public List<RecipeLatest> findRecentsByIdUser(int id, Pageable pageable) {
        return recipeDAO.findRecentsByIdUser(id, pageable);
    }

    @Override
    public List<RecipeLatest> findCardsByAverangeRankingAndIdUser(int id, Pageable pageable) {
        return recipeDAO.findCardsByAverangeRankingAndIdUser(id, pageable);
    }

    @Override
    public Page<RecipeCard> findAllByName(String term, int idCategory, Pageable pageable) {
        return recipeDAO.findAllByName(term, idCategory, pageable);
    }

}
