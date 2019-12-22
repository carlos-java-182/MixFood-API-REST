package com.mixfood.apirest.models.services;

import java.util.List;


import com.mixfood.apirest.projections.TagShort;
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
	public Tag findById(int id)
	{
		 return tagDao.findById(id).orElse(null);
	}

	@Override

	public Tag save(Tag tag) 
	{
		return tagDao.save(tag);
	}

	@Override
	public void delete(int id)
	{
		tagDao.deleteById(id);
	}

	@Override
	public List<TagShort> finAllShort() {
		return tagDao.finAllShort();
	}


}
