package com.mixfood.apirest.models.services;

import java.util.List;

import com.mixfood.apirest.entity.Tag;

public interface ITagService 
{
	public List<Tag> findAll();
	
	public Tag findById(Long id);
	
	public Tag save(Tag tag);
	
	public void delete(Long id);
	
	
}
