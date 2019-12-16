package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeDAO extends CrudRepository<Recipe,Integer>
{

}
