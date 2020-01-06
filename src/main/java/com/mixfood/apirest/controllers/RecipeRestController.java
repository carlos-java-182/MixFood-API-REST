package com.mixfood.apirest.controllers;

import com.mixfood.apirest.entity.Category;
import com.mixfood.apirest.entity.Recipe;
import com.mixfood.apirest.entity.RecipeIngredient;
import com.mixfood.apirest.entity.User;
import com.mixfood.apirest.models.services.*;
import com.mixfood.apirest.projections.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
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
    //*Consts declaration
    private final int ELEMENTS_PAGE = 12;
    //*Objects declaration
    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserService userService;
    @Autowired
    private TagService tagService;
    @Autowired
    private RecipeIngredientService recipeIngredientService;

    @Autowired
    CategoryService categoryService;

    //*Url route

    @GetMapping("/recipes")
    /**
     *
     */
    public List<Recipe> index()
    {
        return recipeService.findAll();
    }


    /**
     *
     * @param id
     * @param size
     * @return
     */
    @GetMapping("/recipes/latests/{id}/items/{size}")
    public List<RecipeLatest> indexRecipeLatests(@PathVariable int id, @PathVariable int size)
    {
        Pageable pageable  = PageRequest.of(0,size);
        return recipeService.findRecentsByIdUser(id, pageable);
    }

    /**
     *
     * @param id
     * @param size
     * @return
     */
    @GetMapping("/recipes/users/latests/{id}/items/{size}")
    public ResponseEntity<?> showLatestUser(@PathVariable int id, @PathVariable int size)
    {
        //*Objects declaration
        List<RecipeLatestUser> recipes;
        Map<String,Object> response = new HashMap<>();

        try
        {
            Pageable pageable = PageRequest.of(0,size);
            //*Find recipes and save in object recipes
            recipes = recipeService.findLatestsByIdUser(id,pageable);
            System.out.println("RECIPES: "+recipes);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error consulting database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //*Id not found
        if(recipes.isEmpty())
        {
            response.put("message","ID: ".concat(String.valueOf(id).concat(" not found!")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<RecipeLatestUser>>(recipes,HttpStatus.OK);


       // Pageable pageable = PageRequest.of(0,size);
        //return recipeService.findLatestsByIdUser(id,pageable);
    }

    /**
     *
     * @param id
     * @param size
     * @return
     */
    @GetMapping("/recipes/cards/featured/{id}/items/{size}")
    public List<RecipeLatest> indexCardsFeatured(@PathVariable int id, @PathVariable int size)
    {
        //*Create object pageable for pagination
        Pageable pageable = PageRequest.of(0,size);
        return recipeService.findCardsByAverangeRankingAndIdUser(id, pageable);
    }

    /**
     *
     * @param term
     * @param id
     * @param page
     * @return
     */
    @GetMapping("/recipes/cards/search/{term}/{id}/page/{page}")
    public Page<RecipeCard> showCardsByNameAndCategory(@PathVariable String term, @PathVariable int id, @PathVariable int page)
    {
        //*Create object pageable for pagination
        Pageable pageable = PageRequest.of(page,ELEMENTS_PAGE);
        return recipeService.findACardsByNameAndCategory(term, id, pageable);
    }

    /**
     *
     * @param term
     * @param page
     * @return
     */
    @GetMapping("/recipes/cards/search/{term}/page/{page}")
    public Page<RecipeCard> showCardsByName(@PathVariable String term,@PathVariable int page)
    {
        //*Create object pageable for pagination
        Pageable pageable = PageRequest.of(page,ELEMENTS_PAGE);
        return recipeService.findCardsByName(term,pageable);
    }

    @GetMapping("/recipes/cards/category/{id}/page/{page}")
    public Page<RecipeCard> showCardsByCategor(@PathVariable int id, @PathVariable int page)
    {
        //*Create object pageable for pagination
        Pageable pageable = PageRequest.of(page,ELEMENTS_PAGE);
        return recipeService.findCardsByCategoryId(id,pageable);
    }
    //*Url route
    @GetMapping("/recipes/search/{term}")
    public List<RecipeSearch> showSearch(@PathVariable String term)
    {
        return recipeService.findLikeName(term);
    }

    //*Url route
    @GetMapping("/recipes/cards")
    public List<RecipeCard> indexCards(@PageableDefault(value=10, page=0) Pageable pageable)
    {
        return  recipeService.findAllForCards(pageable);
    }

    //Create like
    @PostMapping("recipes/like")
    public ResponseEntity<?> createLike(@RequestParam("idRecipe") int idRecipe, @RequestParam("idUser") int idUser)
    {
        //*Objects declaration
        User user = null;
        Recipe recipe = null;
        Map<String,Object> response = new HashMap<>();

        user = userService.findById(idUser);
        recipe = recipeService.findById(idRecipe);

        //*Ids not found
        if(recipe == null || user == null)
        {
            response.put("message","ID recipe: ".concat(String.valueOf(idRecipe).concat(" not found!")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        try
        {
            recipe.getUsersLike().add(user);
            recipeService.save(recipe);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error inserting into database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","The like has been created.");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }

    /**
     **Delete like
     * @param idRecipe
     * @param idUser
     * @return
     */
    @DeleteMapping("recipes/{idRecipe}/like/{idUser}")
    public ResponseEntity<?> deleteLike(@PathVariable int idRecipe, @PathVariable int idUser)
    {
        //*Objects declaration
        User user = null;
        Recipe recipe = null;
        Map<String,Object> response = new HashMap<>();

        user = userService.findById(idUser);
        recipe = recipeService.findById(idRecipe);

        //*Ids not found
        if(recipe == null || user == null)
        {
            response.put("message","ID recipe: ".concat(String.valueOf(idRecipe).concat(" not found!")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        try
        {
            recipe.getUsersLike().remove(user);
            recipeService.save(recipe);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error inserting into database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message","The like has been removed.");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }

    @DeleteMapping("recipes/{id}")
    public ResponseEntity<?> delete(@PathVariable int id)
    {
        //*Objects declaration
        Map<String,Object> response = new HashMap<>();
        Recipe recipe = null;
        Category category = null;

        recipe = recipeService.findById(id);

        if(recipe == null)
        {
            response.put("message","ID: ".concat(String.valueOf(id).concat(" not found!")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        try
        {
            recipe.setStatus("removed");
            recipeService.save(recipe);
            category = categoryService.findById(recipe.getCategory().getId());
            long actualCountCategories = category.getAmountRecipes();
            category.setAmountRecipes(actualCountCategories - 1);

        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error removing into database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message","The recipe has been removed.");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }
    //Find like
    @GetMapping("recipes/{idRecipe}/like/{idUser}")
    public ResponseEntity<?> showLike(@PathVariable int idRecipe, @PathVariable int idUser)
    {
        //*Objects delcaration
        Map<String,Object> response = new HashMap<>();
        Recipe recipe = null;
        User user = null;

        user = userService.findById(idUser);
        recipe = recipeService.findById(idRecipe);

        //*Ids not found
        if(recipe == null || user == null)
        {
            response.put("message","ID recipe: ".concat(String.valueOf(idRecipe).concat(" not found!")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        if(recipe.getUsersLike().contains(user))
        {
            response.put("message","The like of this user already exists.");
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        response.put("message","There is no like of this user for this recipe.");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }

    /**
     **Create recipe
     * @param recipe
     * @param result
     * @return
     */
    @PostMapping("/recipes")
    public ResponseEntity<?> create(@Valid @RequestBody Recipe recipe, BindingResult result)
    {

        //*Objects declaration
        Recipe newRecipe = null;
        User user = null;
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
        response.put("id",newRecipe.getId());
        response.put("recipeName",newRecipe.getName());
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }





    @PostMapping("recipes/ingredients")
    public ResponseEntity<?> createIngredients(@Valid @RequestBody List<RecipeIngredient> recipeIngredients, BindingResult result)
    {
        //*Objects declaration
        RecipeIngredient recipeIngredient1 = null;
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
            //*Save recipe in database and add recipe in the object
            for(RecipeIngredient recipeIngredient: recipeIngredients)
            {
             recipeIngredientService.save(recipeIngredient);
            }

        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error inserting into database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //*Created user response
        response.put("message", "The recipe ingredient has been created");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }

    //*Url route
    @PutMapping("/recipes/views/{idRecipe}/user/{idUser}")
    public ResponseEntity<?> updateView( @PathVariable int idRecipe, @PathVariable int idUser)
    {
        //*Find tag
        Recipe actualRecipe = recipeService.findById(idRecipe);

        //*Create objects
        Recipe updatedRecipe = null;
        Map<String,Object> response = new HashMap<>();

        if(!actualRecipe.getStatus().equals("public") || actualRecipe.getUser().getId() == idUser)
        {
            response.put("message","UPS!!! Your area a bot");
            return  new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
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

    //*Url route
    @GetMapping("/recipes/{id}/profile")
    public ResponseEntity<?> showProfile(@PathVariable int id)
    {
        //*Objects declaration
        RecipeProfile recipeProfile = null;
        Map<String,Object> response = new HashMap<>();

        try
        {
            //*Find user and save in object user
            recipeProfile = recipeService.findProfileById(id);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error consulting database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //*Id not found
        if(recipeProfile == null)
        {
            response.put("message","ID: ".concat(String.valueOf(id).concat(" not found!")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<RecipeProfile>(recipeProfile,HttpStatus.OK);
    }

    @GetMapping("/recipes/cards/tag/{id}/page/{page}")
    public Page<TagRecipeCard> showCardsByidCategory(@PathVariable int id, @PathVariable int page)
    {
        //*Create object pageable for pagination
        Pageable pageable = PageRequest.of(page,12);
        return tagService.findCardsById(id,pageable);
    }


    /**Recipes user routes**/

    /**
     *
     * @param id
     * @param status
     * @param page
     * @param size
     * @return
     */

    @GetMapping("/recipes/user/{id}/{status}/page/{page}/items/{size}")
    public ResponseEntity<?> indexUser(@PathVariable int id, @PathVariable String status, @PathVariable int page, @PathVariable int size)
    {
        //*Objects declaration
        Map<String,Object> response = new HashMap<>();
        Page<RecipeCardTable> recipeCardTables  = null;

        if(!status.equals("private") && !status.equals("public"))
        {
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        try
        {
            Pageable pageable = PageRequest.of(page,size);
            recipeCardTables =  recipeService.findAllByIdUserAndStatusOrderByCreateAt(id,status,pageable);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error consulting database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Page<RecipeCardTable>>(recipeCardTables,HttpStatus.OK);
    }

    @GetMapping("/recipes/user/{id}/{status}/name/{name}/page/{page}/items/{size}")
    public ResponseEntity<?> indexUserLikeName(@PathVariable int id, @PathVariable String name, @PathVariable String status, @PathVariable int page, @PathVariable int size)
    {
        //*Objects declaration
        Map<String,Object> response = new HashMap<>();
        Page<RecipeCardTable> recipeCardTables  = null;

        if(!status.equals("private") && !status.equals("public"))
        {
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        try
        {
            Pageable pageable = PageRequest.of(page,size);
            recipeCardTables =   recipeService.findAllCardsTableByIdUserAndLikeName(id,status,name,pageable);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error consulting database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Page<RecipeCardTable>>(recipeCardTables,HttpStatus.OK);
    }




}

