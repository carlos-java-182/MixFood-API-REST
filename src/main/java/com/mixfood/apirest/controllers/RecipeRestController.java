package com.mixfood.apirest.controllers;

import com.mixfood.apirest.entity.Recipe;
import com.mixfood.apirest.entity.User;
import com.mixfood.apirest.models.services.RecipeService;
import com.mixfood.apirest.models.services.TagService;
import com.mixfood.apirest.models.services.UserService;
import com.mixfood.apirest.projections.RecipeCard;
import com.mixfood.apirest.projections.RecipeSearch;
import com.mixfood.apirest.projections.TagShort;
import com.mixfood.apirest.projections.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//*Set cross origin for angular server
@CrossOrigin(origins = {"http://localhost:4200"})
//*
@RestController
//*Path mapping
@RequestMapping("/api")
public class RecipeRestController
{
    //*Objects declaration
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    //*Url route
    @GetMapping("/recipes")
    public List<Recipe> index()
    {
        return recipeService.findAll();
    }

    //*Url route
    @GetMapping("/recipes/search/{term}")
    public List<RecipeSearch> showSearch(@PathVariable String term)
    {
        return recipeService.findLikeName(term);
    }
    //*Url route
    @GetMapping("/recipes/cards")
    public List<RecipeCard> indexCards()
    {
        //*Variables declaration
        int id = 0;
        //*Get recipes


        return  recipeService.findTest();
    }

    //*Url route
    @PostMapping("/recipes")
    public ResponseEntity<?> create(@Valid @RequestBody Recipe recipe, BindingResult result)
    {
        //*Objects declaration
        Recipe newRecipe = null;
        User user = null;
        Map<String,Object> response = new HashMap<>();

        user = userService.findById(1);
        recipe.setUser(user);
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
            //*Save recipe in database and add recipe in the object
            newRecipe = recipeService.save(recipe);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error inserting into database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //*Created user response
        response.put("message", "The recipe has been created");
       // response.put("recipe", recipe);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }

    //*Url route
    @PutMapping("/recipes/views/{id}")
    public ResponseEntity<?> updateView( @PathVariable int id)
    {
        //*Find tag
        Recipe actualRecipe = recipeService.findById(id);

        //*Create objects
        Recipe updatedRecipe = null;
        Map<String,Object> response = new HashMap<>();

        try
        {
            //*Add view
            actualRecipe.setViews(actualRecipe.getViews() + 1);
            //*Update user
            updatedRecipe = recipeService.save(actualRecipe);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error updating view in database!");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            //*Return response and http status
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //*Add success response to map
        response.put("message", "The views has been updated!");
        //*Return response
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }

    //*Url route
    @GetMapping("/recipes/{id}")
    public ResponseEntity<?> show(@PathVariable int id)
    {
        //*Objects declaration
        Recipe recipe = null;
        Map<String,Object> response = new HashMap<>();

        try
        {
            //*Find user and save in object user
            recipe = recipeService.findById(id);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error consulting database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //*Id not found
        if(recipe == null)
        {
            response.put("message","ID: ".concat(String.valueOf(id).concat(" not found!")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Recipe>(recipe,HttpStatus.OK);
    }


}

