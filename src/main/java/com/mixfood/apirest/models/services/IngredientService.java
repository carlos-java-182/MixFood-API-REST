package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IngredientService {
    //*
    public List<Ingredient> findAll();
    //*
    public Ingredient findById(int id);
    //*
    public Ingredient save(Ingredient ingredient);
    //*
    public void delete(int id);
    public Page<Ingredient> findAllPaginate(Pageable pageable);
    public Page<Ingredient> findPaginateByLikeName(String term, Pageable pageable);
    public Ingredient findByName(String name);

}
