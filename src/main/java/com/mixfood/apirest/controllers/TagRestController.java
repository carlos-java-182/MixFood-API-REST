package com.mixfood.apirest.controllers;

import java.util.*;

import com.mixfood.apirest.projections.TagName;
import com.mixfood.apirest.projections.TagRecipeCard;
import com.mixfood.apirest.projections.TagShort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.mixfood.apirest.entity.Tag;
import com.mixfood.apirest.models.services.TagService;

import javax.validation.Valid;

//*Set cross origin for angular server
@CrossOrigin(origins = {"http://localhost:4200"})
//*
@RestController
//*Path mapping
@RequestMapping("/api")
public class TagRestController 
{
	@Autowired
	private TagService tagService;

	//*Url route
	@GetMapping("/tags")
	public List<Tag> index()
	{
		return tagService.findAll();
	}

	//*Url route
	@GetMapping("/tags/short")
	public List<TagShort> indexShort()
	{
		return tagService.finAllShort();
	}

	//*Url route
	@GetMapping("/tags/{id}")
	public ResponseEntity<?> show(@PathVariable int id)
	{
		//*Objects declaration
		Tag tag = null;
		Map<String,Object> response = new HashMap<>();

		try
		{
			//*Find tag and save in object tag
			tag = tagService.findById(id);
		}
		catch(DataAccessException e)
		{
			//*Response database error
			response.put("message","Error consulting database");
			response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		//*Id not found
		if(tag == null)
		{
			response.put("message","ID: ".concat(String.valueOf(id).concat(" not found!")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Tag>(tag,HttpStatus.OK);
	}

	//!Deprecated method
	@GetMapping("/tags/{id}/recipes/page/{page}")
	public Page<TagRecipeCard> showCardsRecipesById(@PathVariable int id, @PathVariable int page)
	{
		//*Create object pageable for pagination
		Pageable pageable = PageRequest.of(page,12);
		return tagService.findCardsById(id,pageable);
	}

	@GetMapping("tags/{id}/name")
	public ResponseEntity<?> showTagNameById(@PathVariable int id)
	{
		//*Objects declaration
		TagName tagName = null;
		Map<String,Object> response = new HashMap<>();
		try
		{
			//*Get tag name
			tagName = tagService.findNameById(id);
		}
		catch(DataAccessException e)
		{
			//*Response database error
			response.put("message","Error consulting database");
			response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//*Id not found
		if(tagName == null)
		{
			response.put("message","ID: ".concat(String.valueOf(id).concat(" not found!")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TagName>(tagName,HttpStatus.OK);
	}
	//*Url route
	@PostMapping("/tags")
	public ResponseEntity<?> create(@Valid @RequestBody Tag tag, BindingResult result)
	{
		//*Objects declaration
		Tag newTag = null;
		Map<String,Object> response = new HashMap<>();

		//*Validate errors
		if(result.hasErrors())
		{
			//*List declaration
			List<String> errors = new ArrayList<>();
			//*Get errors and add to list
			for(FieldError err : result.getFieldErrors())
			{
				errors.add("Field '"+err.getField()+"' "+err.getDefaultMessage());
			}

			//*Add errors list to response map
			response.put("errors", errors);
			//*Return response
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try
		{
			//*Save user in database and add user in the object
			newTag = tagService.save(tag);
		}
		catch(DataAccessException e)
		{
			//*Response database error
			response.put("message","Error inserting into database");
			response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		//*Created user response
		response.put("message", "The user has been created");
		response.put("tag", newTag);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}

	//*Url route
	@PutMapping("/tags/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Tag tag, BindingResult result, @PathVariable int id)
	{
		//*Find tag
		Tag actualTag = tagService.findById(id);
		//*Create objects
		Tag updatedTag = null;
		Map<String,Object> response = new HashMap<>();

		//*Validate errors
		if(result.hasErrors())
		{
			//*List declaration
			List<String> errors = new ArrayList<>();
			//*Get errors and add to list
			for(FieldError err : result.getFieldErrors())
			{
				errors.add("Field '"+err.getField()+"' "+err.getDefaultMessage());
			}

			//*Add errors list to response map
			response.put("errors", errors);
			//*Return response
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}

		//*Validate if tag does exist
		if(tag == null)
		{
			//*Add message to map
			response.put("message","ID: ".concat(String.valueOf(id).concat(" does not exist!")));
			//*Return response with http status
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}

		try
		{
			//*Add new tag data to actual tag
		//	actualTag.setId(tag.getId());
			actualTag.setName(tag.getName());
			actualTag.setThumbRoute(tag.getThumbRoute());
			actualTag.setUpdateAt(new Date());

			//*Update user
			updatedTag = tagService.save(actualTag);
		}
		catch(DataAccessException e)
		{
			//*Response database error
			response.put("message","Error updating user in database!");
			response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			//*Return response and http status
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		//*Add success response to map
		response.put("message", "The tag has been updated!");
		response.put("tag", updatedTag);
		//*Return response
		return new ResponseEntity<Tag>(updatedTag,HttpStatus.CREATED);
	}



	//*Url route
	@PutMapping("/tags/mention/{id}")
	public ResponseEntity<?> updateMention( @PathVariable int id)
	{
		//*Find tag
		Tag actualTag = tagService.findById(id);

		//*Create objects
		Tag updatedTag = null;
		Map<String,Object> response = new HashMap<>();

		try
		{
			//*Add mention
			actualTag.setMentions(actualTag.getMentions() + 1);
			//*Update user
			updatedTag = tagService.save(actualTag);
		}
		catch(DataAccessException e)
		{
			//*Response database error
			response.put("message","Error updating tag in database!");
			response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			//*Return response and http status
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		//*Add success response to map
		response.put("message", "The mentions has been updated!");
		//response.put("tag", updatedTag);
		//*Return response
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}

	//*Url route
	@DeleteMapping("/tags/{id}")
	public ResponseEntity<?> delete(@PathVariable int id)
	{
		//*Object declaration
		Map<String,Object> response = new HashMap<>();

		try
		{
			//*Delete tag
			tagService.delete(id);
		}
		catch(DataAccessException e)
		{
			//*Response database error
			response.put("message","Error removing user from database!");
			response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//*Return response delete success
		response.put("message", "The tag has been removed!");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
}
