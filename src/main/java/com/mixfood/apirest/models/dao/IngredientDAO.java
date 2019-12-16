package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientDAO extends CrudRepository<Ingredient,Integer> {

}
