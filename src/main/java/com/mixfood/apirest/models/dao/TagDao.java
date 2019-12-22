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
}
