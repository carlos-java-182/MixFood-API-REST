package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Recipe;
import com.mixfood.apirest.models.dao.RecipeDAO;
import com.mixfood.apirest.projections.*;
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
  //  @Transactional(readOnly = true)
    public Recipe findById(int id) {
        return recipeDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Recipe save(Recipe recipe) {
        return recipeDAO.save(recipe);
    }

    @Override
    @Transactional
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
    public Page<RecipeCard> findACardsByNameAndCategory(String term, int idCategory, Pageable pageable) {
        return recipeDAO.findACardsByNameAndCategory(term, idCategory, pageable);
    }

    @Override
    public List<RecipeLatestUser> findLatestsByIdUser(int id, Pageable pageable) {
        return recipeDAO.findLatestsByIdUser(id, pageable);
    }

    @Override
    public Page<RecipeCard> findCardsByName(String term, Pageable pageable) {
        return recipeDAO.findCardsByName(term,pageable);
    }

    @Override
    public Page<RecipeCard> findCardsByCategoryId(int id, Pageable pageable) {
        return recipeDAO.findCardsByCategoryId(id, pageable);
    }

    @Override
    public RecipeProfile findProfileById(int id) {
        return recipeDAO.findProfileById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RecipeCardTable> findAllByIdUserAndStatusOrderByCreateAt(int id,String status, Pageable pageable)
    {
        return recipeDAO.findAllByIdUserAndStatusOrderByCreateAt(id,status,pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RecipeCardTable> findAllCardsTableByIdUserAndLikeName(int id, String status, String name, Pageable pageable) {
        return recipeDAO.findAllCardsTableByIdUserAndLikeName(id,status,name,pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public RecipeEdit findEditById(int id) {
        return recipeDAO.findEditById(id);
    }

    @Override
    public Recipe FindByIdUserAndIdRecipe(int idUser, int idRecipe) {
        return recipeDAO.FindByIdUserAndIdRecipe(idUser,idRecipe);
    }
}
