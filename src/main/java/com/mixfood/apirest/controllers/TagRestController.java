package com.mixfood.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mixfood.apirest.entity.Tag;
import com.mixfood.apirest.models.services.ITagService;

@RestController
@RequestMapping("/api")
public class TagRestController 
{
	@Autowired
	private ITagService tagService; 
	
	@GetMapping("/tags")
	public List<Tag> index()
	{
		return tagService.findAll();
	}
}
