package com.mixfood.apirest.controllers;

import com.mixfood.apirest.entity.Follower;
import com.mixfood.apirest.entity.User;
import com.mixfood.apirest.models.services.FollowerService;
import com.mixfood.apirest.models.services.UserService;
import com.mixfood.apirest.projections.FollowerId;
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
@RequestMapping("/api/")
public class FollowerRestController
{
    @Autowired
    //*Object declaration
    private FollowerService followerService;
    @Autowired
    private UserService userService;

    //*Url route
    @GetMapping("followers/{id}")
    public List<Follower> index(@PathVariable int id)
    {
        return followerService.findFollowersByIdUser(id);
    }

    @GetMapping("followers/validate/user/{idUser}/follower/{idFollower}")
    public FollowerId showValidate(@PathVariable int idUser, @PathVariable int idFollower)
    {
        return  followerService.findByIdUserAndIdFollower(idUser,idFollower);
    }

    @PostMapping("followers")
    public ResponseEntity<?> create(@Valid @RequestBody Follower follower, BindingResult result)
    {
        //*Objects declaration
        Follower newFollower = null;
        Map<String,Object> response = new HashMap<>();

        User user = userService.findById(follower.getUser().getId());
        User follow = userService.findById(follower.getFollower().getId());
        follower.setUser(user);
        follower.setFollower(follow);

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
            //*Save follower in database and add follower in the object
            newFollower = followerService.save(follower);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error inserting into database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //*Created user response
        response.put("message", "The follower has been created");
        response.put("id", newFollower.getId());
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("followers/{id}")
    public ResponseEntity<?> delete(@PathVariable int id)
    {
        //*Object declaration
        Map<String,Object> response = new HashMap<>();

        try
        {
            //*Delete user
            followerService.delete(id);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error removing follower from database!");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //*Return response delete success
        response.put("message", "The follower has been removed!");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }
}
