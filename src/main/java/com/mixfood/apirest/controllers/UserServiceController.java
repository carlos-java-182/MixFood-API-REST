package com.mixfood.apirest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mixfood.apirest.entity.User;
import com.mixfood.apirest.models.services.UserService;

//*Set cross origin for angular server
@CrossOrigin(origins = {"http://localhost:4200"})
//*
@RestController
//*Path mapping
@RequestMapping("/api")
public class UserServiceController 
{
	@Autowired
	//Declare object
	private UserService userService;
	
	//Url route
	@GetMapping("/users")
	public List<User> index()
	{
		return userService.findAll();
	}

	//Url route
	@GetMapping("/users/{id}")
	public ResponseEntity<?> show(@PathVariable int id)
	{
		User user = null; 
		Map<String,Object> response = new HashMap<>();
		
		try 
		{
			user = userService.findById(id);
		}
		catch(DataAccessException e)
		{
			response.put("message","Error consulting database");
			response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(user == null)
		{
			response.put("message","ID: ".concat(String.valueOf(id).concat(" not found!")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	//Url route
	@PostMapping("/users")
	public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result)
	{
		User newUser = null; 
		Map<String,Object> response = new HashMap<>();
		//Validate errors
		if(result.hasErrors())
		{
			//List declaration
			List<String> errors = new ArrayList<>();
			//Get errors and add to list
			for(FieldError err : result.getFieldErrors())
			{
				errors.add("Field '"+err.getField()+"' "+err.getDefaultMessage());
			}
					
			//Add errors list to response map
			response.put("errors", errors);
			//Return response
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try 
		{
			newUser = userService.save(user);		
		}
		catch(DataAccessException e)
		{
			response.put("message","Error inserting into database");
			response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		//Created user response
		response.put("message", "The user has been created");
		response.put("user", newUser);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	//Url route
	@PutMapping("/users/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result, @PathVariable int id)
	{
		//Find user
		User actualUser = userService.findById(id);
		//Create objects
		User updatedUser = null;
		Map<String,Object> response = new HashMap<>();
		
		//Validate errors
		if(result.hasErrors())
		{
			//List declaration
			List<String> errors = new ArrayList<>();
			//Get errors and add to list
			for(FieldError err : result.getFieldErrors())
			{
				errors.add("Field '"+err.getField()+"' "+err.getDefaultMessage());
			}
					
			//Add errors list to response map
			response.put("errors", errors);
			//Return response
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		//Validate if user does exist
		if(user == null)
		{
			//Add message to map
			response.put("message","ID: ".concat(String.valueOf(id).concat(" does not exist!")));
			//Return response with http status
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		//
		try 
		{
			//Add new user data to actual user
			actualUser.setName(user.getName());
			actualUser.setAge(user.getAge());
			actualUser.setLastname(user.getLastname());
			actualUser.setStatus(user.getStatus());
			actualUser.setDescription(user.getDescription());
			actualUser.setPorfileimageRoute(user.getPorfileimageRoute());
			actualUser.setSex(user.getSex());
		
			//Update user
			updatedUser = userService.save(actualUser);
		}
		catch(DataAccessException e)
		{
			//Add errors messages to map 
			response.put("message","Error updating user in database!");
			response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			//Return response and http status
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		//Add success response to map 
		response.put("message", "The user has been updated!");
		response.put("user", updatedUser);
		//Return response
		return new ResponseEntity<User>(updatedUser,HttpStatus.CREATED);
	}
	
	//Url route
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> delete(@PathVariable int id)
	{
		Map<String,Object> response = new HashMap<>();
		
		try
		{
			userService.delete(id);
		}
		catch(DataAccessException e)
		{
			response.put("message","Error removing user from database!");
			response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "The user has been removed!");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);	
	}
}
