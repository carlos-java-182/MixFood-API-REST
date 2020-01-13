package com.mixfood.apirest.models.services;

import java.util.List;

import com.mixfood.apirest.entity.Tag;
import com.mixfood.apirest.projections.TagName;
import com.mixfood.apirest.projections.TagRecipeCard;
import com.mixfood.apirest.projections.TagShort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService
{
	public List<Tag> findAll();
	
	public Tag findById(int id);
	
	public Tag save(Tag tag);
	
	public void delete(int id);

	public List<TagShort> finAllShort();

	public List<TagShort> findAllByIdRecipe(int id);

	public Page<TagRecipeCard> findCardsById(int id, Pageable pageable);

	public TagName findNameById(int id);

	public Page<TagShort> findALLTrendings(Pageable pageable);

	public Page<Tag> findAllPaginate(Pageable pageable);

	public Page<Tag> findPaginateByLikeName(String term, Pageable pageable);

	public Tag findByName(String name);





}
