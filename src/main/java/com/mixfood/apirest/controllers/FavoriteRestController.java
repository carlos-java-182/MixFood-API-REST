package com.mixfood.apirest.controllers;

import com.mixfood.apirest.entity.Favorite;
import com.mixfood.apirest.entity.Recipe;
import com.mixfood.apirest.entity.User;
import com.mixfood.apirest.models.services.FavoriteService;
import com.mixfood.apirest.models.services.RecipeService;
import com.mixfood.apirest.models.services.UserService;
import com.mixfood.apirest.projections.FavoriteCard;
import com.mixfood.apirest.projections.FavoriteId;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//*Set cross origin for angular server
@CrossOrigin(origins = {"http://localhost:4200"})
//*
@RestController
//*Path mapping
@RequestMapping("/api/")
public class FavoriteRestController {
    @Autowired
    //*Object declaration
    private FavoriteService favoriteService;

    @Autowired
    private UserService userService;

    @Autowired
    private RecipeService recipeService;

    //*Url route
    @GetMapping("favorites/{id}/page/{page}/items/{items}")
    public Page<FavoriteCard> index(@PathVariable int id, @PathVariable int page, @PathVariable int items)
    {
        Pageable pageable = PageRequest.of(page,items);
        return favoriteService.findAllByIdUser(id,pageable);
    }

    @Secured("ROLE_USER")
    @GetMapping("favorites/validate/recipe/{idRecipe}/user/{idUser}")
    public ResponseEntity<?> validate(@PathVariable int idRecipe, @PathVariable int idUser)
    {
        FavoriteId favorite = null;
        favorite = favoriteService.findIdbyIdUserAndIdRecipe(idUser,idRecipe);
        Map<String,Object> response = new HashMap<>();
        if(favorite != null)
        {
            response.put("message","This favorite already exists.");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        response.put("message", "The favorite not exist");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }


    @PostMapping("favorites")
    public ResponseEntity<?> create(@Valid @RequestBody Favorite favorite, BindingResult result)
    {
        //*Objects declaration
        Favorite newFavorite = null;
        FavoriteId favoriteId = null;
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

        favoriteId = favoriteService.findIdbyIdUserAndIdRecipe(favorite.getUser().getId(),favorite.getRecipe().getId());

        if(favoriteId != null)
        {
            response.put("message","This favorite already exists.");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        try
        {
            //*Save recipe in database and add recipe in the object
            newFavorite = favoriteService.save(favorite);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error inserting into database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //*Created user response
        response.put("message", "The favorite has been created");
        //response.put("favorite",newFavorite);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);

    }

    @GetMapping("favorites/recipe/{idRecipe}/user/{idUser}")
    public ResponseEntity<?> show(@PathVariable int idRecipe,@PathVariable int idUser)
    {
        //*Object declaration
        Map<String,Object> response = new HashMap<>();
        FavoriteId favoriteId = null;
        try
        {
            //*Find favorite
            favoriteId = favoriteService.findIdbyIdUserAndIdRecipe(idUser,idRecipe);

        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error find favorite from database!");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(favoriteId == null)
        {
            response.put("message","This favorite not exists.");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        //*Return response delete success
       // response.put("message", "The favorite has been removed!");
        response.put("id",favoriteId.getId());
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }

    //*Url route
    @DeleteMapping("favorites/recipe/{idRecipe}/user/{idUser}")
    public ResponseEntity<?> delete(@PathVariable int idRecipe,@PathVariable int idUser)
    {
        //*Object declaration
        Map<String,Object> response = new HashMap<>();
        FavoriteId favoriteId = favoriteService.findIdbyIdUserAndIdRecipe(idUser,idRecipe);

        if(favoriteId == null)
        {
            response.put("message","This favorite not exists.");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        try
        {
            //*Delete favorite
            favoriteService.delete(favoriteId.getId());
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error removing favorite from database!");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //*Return response delete success
        response.put("message", "The favorite has been removed!");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }
}
