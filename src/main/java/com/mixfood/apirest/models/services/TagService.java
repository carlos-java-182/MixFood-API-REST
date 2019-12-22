package com.mixfood.apirest.models.services;

import java.util.List;

import com.mixfood.apirest.entity.Tag;
import com.mixfood.apirest.projections.TagShort;

public interface TagService
{
	public List<Tag> findAll();
	
	public Tag findById(int id);
	
	public Tag save(Tag tag);
	
	public void delete(int id);

	public List<TagShort> finAllShort();

	public List<TagShort> findAllByIdRecipe(int id);

	
}
