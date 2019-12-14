package com.mixfood.apirest.models.services;

import java.util.List;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mixfood.apirest.entity.Tag;
import com.mixfood.apirest.models.dao.ITagDao;

@Service
public class TagServiceImpl implements ITagService
{
	@Autowired
	private ITagDao tagDao;

	@Override
	@Transactional(readOnly = true)
	public List<Tag> findAll()
	{
		return (List<Tag>) tagDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Tag findById(Long id)
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
	public void delete(Long id) 
	{
		tagDao.deleteById(id);
		
	}
	

}
