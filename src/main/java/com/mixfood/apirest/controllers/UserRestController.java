package com.mixfood.apirest.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;

import com.mixfood.apirest.entity.PasswordChange;
import com.mixfood.apirest.entity.SocialNetwork;
import com.mixfood.apirest.models.dao.SocialNetworkDAO;
import com.mixfood.apirest.models.services.SocialNetworkService;
import com.mixfood.apirest.projections.SocialNetworkList;
import com.mixfood.apirest.projections.UserEmail;
import com.mixfood.apirest.projections.UserInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.mixfood.apirest.entity.User;
import com.mixfood.apirest.models.services.UserService;
import org.springframework.web.multipart.MultipartFile;

//*Set cross origin for angular server
@CrossOrigin(origins = {"http://localhost:4200"})
//*
@RestController
//*Path mapping
@RequestMapping("/api")
public class UserRestController
{
	@Autowired
	//*Declare object
	private UserService userService;

	@Autowired
	private SocialNetworkService socialNetworkService;


	//*Url route
	@GetMapping("/users")
	public List<User> index()
	{
		return userService.findAll();
	}

	//*Url route
	@GetMapping("/users/{id}")
	public ResponseEntity<?> show(@PathVariable int id)
	{
		//*Objects declaration
		User user = null; 
		Map<String,Object> response = new HashMap<>();
		
		try 
		{
			//*Find user and save in object user
			user = userService.findById(id);
		}
		catch(DataAccessException e)
		{
			//*Response database error
			response.put("message","Error consulting database");
			response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		//*Id not found
		if(user == null)
		{
			response.put("message","ID: ".concat(String.valueOf(id).concat(" not found!")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}

	@GetMapping("/users/settings/information/{id}")
	public ResponseEntity<?> showInformation(@PathVariable int id)
	{
		//*Objects declaration
		UserInformation userInformation = null;
		Map<String,Object> response = new HashMap<>();

		try
		{
			//*Find user and save in object user
			userInformation = userService.findInformationById(id);
		}
		catch(DataAccessException e)
		{
			//*Response database error
			response.put("message","Error consulting database");
			response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		//*Id not found
		if(userInformation == null)
		{
			response.put("message","ID: ".concat(String.valueOf(id).concat(" not found!")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserInformation>(userInformation,HttpStatus.OK);
	}

	@GetMapping("/users/settings/email/{id}")
	public ResponseEntity<?> showEmail(@PathVariable int id)
	{
		//*Objects declaration
		UserEmail userEmail = null;
		Map<String,Object> response = new HashMap<>();

		try
		{
			//*Find user and save in object user
			userEmail = userService.findEmailById(id);
			System.out.println("EMAIL: "+userEmail);
		}
		catch(DataAccessException e)
		{
			//*Response database error
			response.put("message","Error consulting database");
			response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		//*Id not found
		if(userEmail == null)
		{
			response.put("message","ID: ".concat(String.valueOf(id).concat(" not found!")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserEmail>(userEmail,HttpStatus.OK);
	}

	@GetMapping("/users/settings/socialnetworks/{id}")
	public ResponseEntity<?> showSocialNetworks(@PathVariable int id)
	{
		//*Objects declaration
		List<SocialNetworkList> socialNetworkList = null;
		Map<String,Object> response = new HashMap<>();
		try
		{
			//*Find user and save in object user
			socialNetworkList = socialNetworkService.findListbyId(id);
		}
		catch(DataAccessException e)
		{
			//*Response database error
			response.put("message","Error consulting database");
			response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		//*Id not found
		if(socialNetworkList == null)
		{
			response.put("message","ID: ".concat(String.valueOf(id).concat(" not found!")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<List<SocialNetworkList>>(socialNetworkList,HttpStatus.OK);
	}

	@PutMapping("/users/settings/socialnetworks/{id}")
	public ResponseEntity<?> updateSocialNetworks(@RequestBody SocialNetwork socialNetwork, @PathVariable int id)
	{
		//*Create objects
		SocialNetwork actualNetwork = null;
		User user = null;
		Map<String,Object> response = new HashMap<>();

		user = userService.findById(id);
		actualNetwork = socialNetworkService.findByIdUserAndNetwork(id,socialNetwork.getNetwork());

		try
		{
			//*Add new user data to actual user
			if(actualNetwork == null)
			{
				socialNetwork.setUser(user);
				socialNetworkService.save(socialNetwork);
				//*Add success response to map
				response.put("message", "The socialNetwork has been created!");
				//*Return response
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
			}
			else
			{
				actualNetwork.setLink(socialNetwork.getLink());
				socialNetworkService.save(actualNetwork);
				//*Add success response to map
				response.put("message", "The socialNetwork has been update!");
				//*Return response
				return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
			}
		}
		catch(DataAccessException e)
		{
			//*Response database error
			response.put("message","Error updating user in database!");
			response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			//*Return response and http status
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}

	@DeleteMapping("/users/settings/socialnetworks/{network}/{id}")
	public ResponseEntity<?> deleteSocialNetworks(@PathVariable SocialNetwork.Network network, @PathVariable int id)
	{
		//*Create objects
		SocialNetwork actualNetwork = null;
		Map<String,Object> response = new HashMap<>();

		try
		{
			actualNetwork = socialNetworkService.findByIdUserAndNetwork(id,network);
			int idSocial = actualNetwork.getId();
			socialNetworkService.delete(idSocial);
		}
		catch(DataAccessException e)
		{
			//*Response database error
			response.put("message","Error removing socialnetwork in database!");
			response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			//*Return response and http status
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		//*Return response delete success
		response.put("message", "The socialnetwork has been removed!");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
	}
	//*Url route
	@PostMapping("/users")
	public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result)
	{
		//*Objects declaration
		User newUser = null; 
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
			newUser = userService.save(user);		
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
		response.put("user", newUser);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}

	@PutMapping("/users/settings/information/{id}")
	public ResponseEntity<?> updateInformation(@RequestBody User userInformation, @PathVariable int id)
	{
		//*Create objects
		User actualUser = null;
		User updatedUser = null;
		Map<String,Object> response = new HashMap<>();

		//*Find user by id
		actualUser = userService.findById(id);

		System.out.println("NAME: "+actualUser.getName());

		//*Validate if user does exist
		if(actualUser == null)
		{
			//*Add message to map
			response.put("message","ID: ".concat(String.valueOf(id).concat(" does not exist!")));
			//*Return response with http status
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}

		try
		{
			//*Add new user data to actual user

			actualUser.setName(userInformation.getName());
			actualUser.setLastname(userInformation.getLastname());

			actualUser.setDateBirth(userInformation.getDateBirth());
			actualUser.setDescription(userInformation.getDescription());
			actualUser.setGender(userInformation.getGender());
			actualUser.setCountry(userInformation.getCountry());

			//*Update user
			updatedUser = userService.save(actualUser);
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
		response.put("message", "The user has been updated!");
		response.put("user", updatedUser);
		//*Return response

		return new ResponseEntity<User>(updatedUser,HttpStatus.CREATED);

	}

	@PutMapping("/users/settings/password/{id}")
	public ResponseEntity<?> updatePassword(@RequestBody PasswordChange passwordChange, @PathVariable int id)
	{
		//*Create objects
		User actualUser = null;
		User updatedUser = null;
		Map<String,Object> response = new HashMap<>();

		//*Find user by id
		actualUser = userService.findById(id);

		System.out.println("NAME: "+actualUser.getName());

		//*Validate if user does exist
		if(actualUser == null)
		{
			//*Add message to map
			response.put("message","ID: ".concat(String.valueOf(id).concat(" does not exist!")));
			//*Return response with http status
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}

		System.out.println("PASSWORD: "+actualUser.getPassword().toString());
		System.out.println("PASSWORD: "+passwordChange.getActualPassword());
		System.out.println(actualUser.getPassword().equals("password"));
		//if(actualUser.getPassword() != "password")
		//{
			//*Add message to map
			response.put("error","passwordwrong");
			response.put("message","The actual password is wrong");
			//*Return response with http status
		//	return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		//}
		try
		{
			//*Add new user data to actual user
			actualUser.setPassword(passwordChange.getNewPassword());
			//*Update user
			updatedUser = userService.save(actualUser);
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
		response.put("message", "The password has been updated!");
		//*Return response
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}



	//*Url route
	@PutMapping("/users/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody User user, BindingResult result, @PathVariable int id)
	{
		//*Find user
		User actualUser = userService.findById(id);
		//*Create objects
		User updatedUser = null;
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
		
		//*Validate if user does exist
		if(user == null)
		{
			//*Add message to map
			response.put("message","ID: ".concat(String.valueOf(id).concat(" does not exist!")));
			//*Return response with http status
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}

		try 
		{
			//*Add new user data to actual user
			actualUser.setName(user.getName());
			actualUser.setAge(user.getAge());
			actualUser.setLastname(user.getLastname());
			actualUser.setStatus(user.getStatus());
			actualUser.setDescription(user.getDescription());
			actualUser.setPorfileimageRoute(user.getPorfileimageRoute());
			actualUser.setGender(user.getGender());
		
			//*Update user
			updatedUser = userService.save(actualUser);
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
		response.put("message", "The user has been updated!");
		response.put("user", updatedUser);
		//*Return response
		return new ResponseEntity<User>(updatedUser,HttpStatus.CREATED);
	}
	
	//*Url route
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> delete(@PathVariable int id)
	{
		//*Object declaration
		Map<String,Object> response = new HashMap<>();
		
		try
		{
			//*Delete user
			userService.delete(id);
		}
		catch(DataAccessException e)
		{
			//*Response database error
			response.put("message","Error removing user from database!");
			response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//*Return response delete success
		response.put("message", "The user has been removed!");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);	
	}

	//*Url route
	@PostMapping("users/upload")
	/**
	 *
	 */
	public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file, @RequestParam("id") int id)
	{
		//*Object declaration
		Map<String,Object> response = new HashMap<>();
		User user = userService.findById(id);
		if (!file.isEmpty())
		{
			String fileName = file.getOriginalFilename();
			Path routeFile = Paths.get("uploads/users").resolve(fileName).toAbsolutePath();
			try {
				Files.copy(file.getInputStream(),routeFile);
			} catch (IOException e) {
				//*Response database error
				response.put("message","Error updating image from database!");
				response.put("error",e.getMessage().concat(" : ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			//*Set photo to user

			//*Update user
			response.put("user",user);
			response.put("message","upload success!");
		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
