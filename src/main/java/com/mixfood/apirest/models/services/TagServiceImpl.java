package com.mixfood.apirest.models.services;

import java.util.List;


import com.mixfood.apirest.projections.TagName;
import com.mixfood.apirest.projections.TagRecipeCard;
import com.mixfood.apirest.projections.TagShort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mixfood.apirest.entity.Tag;
import com.mixfood.apirest.models.dao.TagDao;

@Service
public class TagServiceImpl implements TagService
{
	@Autowired
	private TagDao tagDao;

	@Override
	@Transactional(readOnly = true)
	public List<Tag> findAll()
	{
		return (List<Tag>) tagDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Tag findById(int id)
	{
		 return tagDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Tag save(Tag tag) 
	{
		return tagDao.save(tag);
	}

	@Override
	@Transactional
	public void delete(int id)
	{
		tagDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TagShort> finAllShort()
	{
		return tagDao.finAllShort();
	}

	@Override
	@Transactional(readOnly = true)
	public List<TagShort> findAllByIdRecipe(int id) {
		return tagDao.findAllByIdRecipe(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<TagRecipeCard> findCardsById(int id, Pageable pageable) {
		return tagDao.findCardsById(id, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public TagName findNameById(int id) {
		return tagDao.findNameById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<TagShort> findALLTrendings(Pageable pageable) {
		return tagDao.findALLTrendings(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Tag> findAllPaginate(Pageable pageable) {
		return tagDao.findAllPaginate(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Tag> findPaginateByLikeName(String term, Pageable pageable) {
		return tagDao.findPaginateByLikeName(term, pageable);
	}

	@Override
	public Tag findByName(String name) {
		return tagDao.findByName(name);
	}
}
