package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientDAO extends CrudRepository<Ingredient,Integer>
{
    @Query("SELECT i FROM Ingredient i")
    public Page<Ingredient> findAllPaginate(Pageable pageable);

    @Query("SELECT i FROM Ingredient i WHERE name LIKE :term%")
    public Page<Ingredient> findPaginateByLikeName(String term, Pageable pageable);

    @Query("SELECT i FROM Ingredient i WHERE name = :name")
    public Ingredient findByName(String name);
}
