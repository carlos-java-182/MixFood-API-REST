package com.mixfood.apirest.controllers;

import com.mixfood.apirest.entity.Ranking;
import com.mixfood.apirest.entity.User;
import com.mixfood.apirest.models.services.RankingService;
import com.mixfood.apirest.models.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api")
public class RankingRestController
{
    @Autowired
    RankingService rankingService;

    @Autowired
    UserService userService;

    //*Url route
    @GetMapping("/rankings")
    public List<Ranking> index()
    {
        return rankingService.findAll();
    }

    //*Url route
    @PostMapping("/rankings")
    public ResponseEntity<?> create(@Valid @RequestBody Ranking ranking, BindingResult result)
    {
        //*Objects declaration
        Ranking newRanking = null;
        //User user = null;
        Map<String,Object> response = new HashMap<>();

      //  user = userService.findById(1);
      //  recipe.setUser(user);
        //*Validate errors
       /* if(result.hasErrors())
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
        }*/

        try
        {
            //*Save recipe in database and add recipe in the object
            newRanking = rankingService.save(ranking);
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

}
