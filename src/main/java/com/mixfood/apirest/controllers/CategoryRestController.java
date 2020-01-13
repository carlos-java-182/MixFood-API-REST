package com.mixfood.apirest.controllers;

import com.mixfood.apirest.entity.Category;
import com.mixfood.apirest.entity.Tag;
import com.mixfood.apirest.models.services.CategoryService;
import com.mixfood.apirest.projections.CategoryCard;
import com.mixfood.apirest.projections.CategoryList;
import com.mixfood.apirest.projections.CategoryName;
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

import javax.validation.Valid;
import java.util.*;

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


    @GetMapping("/categories/page/{page}/items/{items}")
    public ResponseEntity<?> showPages(@PathVariable int page, @PathVariable int items)
    {
        //*Objects declaration
        Page<Category> categories;
        Map<String,Object> response = new HashMap<>();

        try
        {
            Pageable pageable = PageRequest.of(page,items);
            //*Find ingredients and save in object category
            categories = categoryService.findAllPaginate(pageable);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error consulting database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Page<Category>>(categories,HttpStatus.OK);
    }

    @GetMapping("categories/page/{page}/items/{items}/term/{term}")
    public ResponseEntity<?> showPagesByTerm(@PathVariable int page, @PathVariable int items, @PathVariable String term)
    {
        //*Objects declaration
        Page<Category> categories;
        Map<String,Object> response = new HashMap<>();

        try
        {
            Pageable pageable = PageRequest.of(page,items);
            //*Find ingredients and save in object recipes
            categories = categoryService.findPaginateByLikeName(term, pageable);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error consulting database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Page<Category>>(categories,HttpStatus.OK);
    }

    //*Url route
    @GetMapping("/categories")
    public List<Category> index()
    {
        return categoryService.findAll();
    }

    //*Url route
    @GetMapping("/categories/cards/page/{page}/items/{items}")
    public Page<CategoryCard> indexCard(@PathVariable int page, @PathVariable int items)
    {
        Pageable pageable = PageRequest.of(page,items);
        return categoryService.findAllCards(pageable);
    }

    @GetMapping("categories/listlimit")
    public Page<CategoryList> indexListLimit()
    {
        Pageable pageable = PageRequest.of(0,10);
        return categoryService.findListLimit(pageable);
    }

    @GetMapping("categories/list")
    public List<CategoryList> indexList()
    {
        return categoryService.findAllList();
    }

    @GetMapping("categories/user/list/{id}/items/{size}")
    public List<CategoryList> showUserList(@PathVariable int id, @PathVariable int size)
    {
        Pageable pageable = PageRequest.of(0,size);
        return categoryService.findListByIdUser(id,pageable);
    }

    @GetMapping("/categories/name/{id}")
    public ResponseEntity<?> showNameById(@PathVariable int id)
    {
        //*Objects declaration
        CategoryName categoryName = null;
        Map<String,Object> response = new HashMap<>();

        try
        {
            //*Find category and save in object category
            categoryName = categoryService.findNameById(id);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error consulting database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //*Id not found
        if(categoryName == null)
        {
            response.put("message","ID: ".concat(String.valueOf(id).concat(" not found!")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CategoryName>(categoryName,HttpStatus.OK);
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

    //*Url route
    @PostMapping("/categories")
    public ResponseEntity<?> create(@Valid @RequestBody Category category, BindingResult result)
    {
        //*Objects declaration
        Category newCategory = null;
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
            newCategory = categoryService.save(category);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error inserting into database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //*Created user response
        response.put("message", "The category has been created");
        response.put("category", newCategory);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }

    //*Url route
    @PutMapping("/categories/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Category category, BindingResult result, @PathVariable int id)
    {
        //*Find user
        Category actualCategory = categoryService.findById(id);
        //*Create objects
        Category updatedCategory = null;
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
        if(category == null)
        {
            //*Add message to map
            response.put("message","ID: ".concat(String.valueOf(id).concat(" does not exist!")));
            //*Return response with http status
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        try
        {
            //*Add new category data to actual category
            actualCategory.setName(category.getName());
            actualCategory.setUpdateAt(new Date());
            //*Update category
            updatedCategory = categoryService.save(actualCategory);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error updating category in database!");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            //*Return response and http status
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //*Add success response to map
        response.put("message", "The category has been updated!");
        response.put("category", updatedCategory);
        //*Return response
        return new ResponseEntity<Category>(updatedCategory,HttpStatus.CREATED);
    }

    //*Url route
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<?> delete(@PathVariable int id)
    {
        //*Object declaration
        Map<String,Object> response = new HashMap<>();

        try
        {
            //*Delete category
            categoryService.delete(id);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error removing category from database!");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //*Return response delete success
        response.put("message", "The category has been removed!");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }

}
