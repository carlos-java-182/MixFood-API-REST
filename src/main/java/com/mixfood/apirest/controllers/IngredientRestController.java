package com.mixfood.apirest.controllers;

import com.mixfood.apirest.entity.Ingredient;
import com.mixfood.apirest.models.dao.RecipeIngredientDAO;
import com.mixfood.apirest.models.services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

//*Set cross origin for angular server
@CrossOrigin(origins = {"http://localhost:4200"})
//*
@RestController
//*Path mapping
@RequestMapping("/api")
public class IngredientRestController
{
    @Autowired
    //*Declare object
    private IngredientService ingredientService;
    @Autowired
    private RecipeIngredientDAO recipeIngredientDAO;
    //*Url route
    @GetMapping("/ingredients")
    public List<Ingredient> index()
    {
        System.out.println("Hello!!!");
        return ingredientService.findAll();
    }

    /**
     **Esta funcion obtiene un ingrediente por su id
     * @param id
     * @return
     */
    @Secured("ROLE_USER")
    @GetMapping("/ingredients/{id}")
    public ResponseEntity<?> show(@PathVariable int id)
    {
        //*Objects declaration
        Ingredient ingredient = null;
        Map<String,Object> response = new HashMap<>();

        try
        {
            //*Find ingredient and save in object ingredient
            ingredient = ingredientService.findById(id);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error consulting database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //*Id not found
        if(ingredient == null)
        {
            response.put("message","ID: ".concat(String.valueOf(id).concat(" not found!")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Ingredient>(ingredient,HttpStatus.OK);
    }

    //*Url route

    @PostMapping("/ingredients")
    public ResponseEntity<?> create(@Valid @RequestBody Ingredient ingredient, BindingResult result)
    {
        //*Objects declaration
        Ingredient newIngredient = null;
        Ingredient actualIngredient = null;
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

        actualIngredient = ingredientService.findByName(ingredient.getName());

        if(actualIngredient != null)
        {
            response.put("message","This ingredient already exists.");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        try
        {
            //*Save ingredient in database and add ingredient in the object
            newIngredient = ingredientService.save(ingredient);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error inserting into database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //*Created user response
        response.put("message", "The ingredient has been created");
        response.put("ingredient", newIngredient);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }


    @GetMapping("ingredients/page/{page}/items/{items}")
    public ResponseEntity<?> showPages(@PathVariable int page, @PathVariable int items)
    {
        //*Objects declaration
        Page<Ingredient> ingredients;
        Map<String,Object> response = new HashMap<>();

        try
        {
            Pageable pageable = PageRequest.of(page,items);
            //*Find ingredients and save in object recipes
            ingredients = ingredientService.findAllPaginate(pageable);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error consulting database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Page<Ingredient>>(ingredients,HttpStatus.OK);
    }

    @GetMapping("ingredients/page/{page}/items/{items}/term/{term}")
    public ResponseEntity<?> showPagesByTerm(@PathVariable int page, @PathVariable int items, @PathVariable String term)
    {
        //*Objects declaration
        Page<Ingredient> ingredients;
        Map<String,Object> response = new HashMap<>();

        try
        {
            Pageable pageable = PageRequest.of(page,items);
            //*Find ingredients and save in object recipes
            ingredients = ingredientService.findPaginateByLikeName(term, pageable);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error consulting database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Page<Ingredient>>(ingredients,HttpStatus.OK);
    }

    //*Url route
    @PutMapping("/ingredients/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Ingredient ingredient, BindingResult result, @PathVariable int id)
    {
        //*Find ingredient
        Ingredient actualIngredient = ingredientService.findById(id);
        Ingredient ingredientExists = null;
        //*Create objects
        Ingredient updatedIngredient = null;
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

        //*Validate if ingredient does exist
        if(ingredient == null)
        {
            //*Add message to map
            response.put("message","ID: ".concat(String.valueOf(id).concat(" does not exist!")));
            //*Return response with http status
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        ingredientExists = ingredientService.findByName(ingredient.getName());
        if(ingredientExists != null)
        {
            response.put("message","This ingredient already exists.");
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        try
        {
            //*Add new ingredient data to actual ingredien
            actualIngredient.setName(ingredient.getName());
            actualIngredient.setUpdateAt(new Date());

            //*Update ingredient
            updatedIngredient = ingredientService.save(actualIngredient);
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
        response.put("ingredient", updatedIngredient);
        //*Return response
        return new ResponseEntity<Ingredient>(updatedIngredient,HttpStatus.CREATED);
    }

    //*Url route
    @DeleteMapping("/ingredients/{id}")
    public ResponseEntity<?> delete(@PathVariable int id)
    {
        //*Object declaration
        Map<String,Object> response = new HashMap<>();

        try
        {
            //*Delete ingredient
            ingredientService.delete(id);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error removing user from database!");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //*Return response delete success
        response.put("message", "The ingredient has been removed!");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }

    @PostMapping("/ingredients/filter")
    public  ResponseEntity<?> filter(@RequestBody  List<Ingredient> ids)
    {
        //*Object declaration
        Map<String,Object> response = new HashMap<>();
        //;
        response.put("results",recipeIngredientDAO.findByIngredients(ids));
        response.put("message", "The ingredient has been removed!");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }
}
