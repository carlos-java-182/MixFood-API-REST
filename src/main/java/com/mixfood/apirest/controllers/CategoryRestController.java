package com.mixfood.apirest.controllers;

import com.mixfood.apirest.entity.Category;
import com.mixfood.apirest.models.services.CategoryService;
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
public class CategoryRestController
{
    @Autowired
    //*Object declaration
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> index()
    {
        return categoryService.findAll();
    }

    //*Url route
    @GetMapping("/categories/{id}")
    public ResponseEntity<?> show(@PathVariable int id)
    {
        //*Objects declaration
        Category category = null;
        Map<String,Object> response = new HashMap<>();

        try
        {
            //*Find category and save in object category
            category = categoryService.findById(id);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error consulting database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //*Id not found
        if(category == null)
        {
            response.put("message","ID: ".concat(String.valueOf(id).concat(" not found!")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Category>(category,HttpStatus.OK);
    }
}
