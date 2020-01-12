package com.mixfood.apirest.controllers;

import com.mixfood.apirest.entity.Ranking;
import com.mixfood.apirest.entity.User;
import com.mixfood.apirest.models.services.RankingService;
import com.mixfood.apirest.models.services.UserService;
import com.mixfood.apirest.projections.RankingComment;
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
    @Secured("ROLE_USER")
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
        response.put("ranking", ranking);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }

    @GetMapping("/rankings/comments/{id}/page/{page}/{items}")
    public Page<RankingComment> showComments(@PathVariable int id,@PathVariable int page, @PathVariable int items)
    {
        Pageable pageable = PageRequest.of(page,items);
        return  rankingService.findByIdRecipe(id,pageable);
    }

    /**
     **This function validate if the ranking of user logged exist
     * @param idRecipe: id recipe
     * @param idUser: id user
     * @return: Message about validation status
     */
    @Secured("ROLE_USER")
    @GetMapping("rankings/validate/{idRecipe}/user/{idUser}")
    public ResponseEntity<?> showValidate(@PathVariable int idRecipe, @PathVariable int idUser)
    {
        System.out.println("ID: "+idRecipe);
        //*Objects declaration
        Ranking ranking = null;
        Map<String,Object> response = new HashMap<>();

        ranking = rankingService.findByIdRecipeAndIdUser(idRecipe,idUser);

        //*Validate if ranking exists
        if(ranking != null)
        {
            response.put("message","This user has already commented previously");
            return  new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }

        //*Created user response
        response.put("message", "Comments no exists");
        response.put("ranking", ranking);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }



}
