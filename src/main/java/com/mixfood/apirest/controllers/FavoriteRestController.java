package com.mixfood.apirest.controllers;

import com.mixfood.apirest.entity.Favorite;
import com.mixfood.apirest.models.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//*Set cross origin for angular server
@CrossOrigin(origins = {"http://localhost:4200"})
//*
@RestController
//*Path mapping
@RequestMapping("/api")
public class FavoriteRestController {
    @Autowired
    //*Object declaration
    private FavoriteService favoriteService;

    //*Url route
    @GetMapping("/favorites/{id}")
    public List<Favorite> index(@PathVariable int id)
    {
        return favoriteService.findAllByIdUser(id);
    }

    //*Url route
    @DeleteMapping("/favorites/{id}")
    public ResponseEntity<?> delete(@PathVariable int id)
    {
        //*Object declaration
        Map<String,Object> response = new HashMap<>();

        try
        {
            //*Delete favorite
            favoriteService.delete(id);
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
