package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeIngredientDAO extends CrudRepository<RecipeIngredient,Integer>
{
    @Query("SELECT DISTINCT ri FROM RecipeIngredient ri WHERE ingredient_id IN :ids")
    public  List<RecipeIngredient> findByIngredients(List ids);
}
