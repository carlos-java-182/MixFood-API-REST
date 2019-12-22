package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.projections.RecipeSearch;
import com.mixfood.apirest.projections.TagShort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mixfood.apirest.entity.Tag;

import java.util.List;

public interface TagDao extends CrudRepository<Tag,Integer>
{
    @Query(value = "SELECT id_tag AS id, name FROM tags;", nativeQuery = true)
    public List<TagShort> finAllShort();

    @Query(value = "SELECT t.id_tag AS id, t.name FROM recipes_tags rt INNER JOIN tags t ON rt.tags_id = t.id_tag\n" +
            "WHERE rt.recipes_id = :id", nativeQuery = true)
    public List<TagShort> findAllByIdRecipe(int id);
}
