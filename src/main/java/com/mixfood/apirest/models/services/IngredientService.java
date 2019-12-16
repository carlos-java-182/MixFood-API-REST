package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Ingredient;

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

}
