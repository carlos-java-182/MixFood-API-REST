package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeDAO extends CrudRepository<Recipe,Integer>
{
    @Query(value =  "SELECT name FROM Recipes",
            nativeQuery = true)
    public List<Object> findAllForCards();

}
