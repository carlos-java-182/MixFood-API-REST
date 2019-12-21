package com.mixfood.apirest.models.services;

import java.util.List;

import com.mixfood.apirest.entity.Tag;

public interface TagService
{
	public List<Tag> findAll();
	
	public Tag findById(int id);
	
	public Tag save(Tag tag);
	
	public void delete(int id);


	
}
