package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.RecipeIngredient;
import org.springframework.data.repository.CrudRepository;

public interface RecipeIngredientDAO extends CrudRepository<RecipeIngredient,Integer>
{
}
