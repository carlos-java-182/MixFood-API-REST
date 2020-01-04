package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Recipe;
import com.mixfood.apirest.projections.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecipeService
{
    public List<Recipe> findAll();
    public Recipe findById(int id);
    public Recipe save(Recipe recipe);
    public void delete(int id);
    public List<RecipeCard> findAllForCards(Pageable pageable);
    public List<RecipeSearch> findLikeName(String term);
    public List<RecipeLatest> findRecentsByIdUser(int id, Pageable pageable);
    public List<RecipeLatest> findCardsByAverangeRankingAndIdUser(int id, Pageable pageable);
    public Page<RecipeCard> findACardsByNameAndCategory(String term, int idCategory, Pageable pageable);
    public List<RecipeLatestUser> findLatestsByIdUser(int id, Pageable pageable);
    public Page<RecipeCard> findCardsByName(String term, Pageable pageable);
    public Page<RecipeCard> findCardsByCategoryId(int id, Pageable pageable);
    public RecipeProfile findProfileById(int id);
    public Page<RecipeCardTable> findAllByIdUserAndStatusOrderByCreateAt(int id,String status, Pageable pageable);
    public Page<RecipeCardTable> findAllCardsTableByIdUserAndLikeName(int id,String status, String name, Pageable pageable);









}
