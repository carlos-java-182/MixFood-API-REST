package com.mixfood.apirest.controllers;

import com.mixfood.apirest.entity.SocialNetwork;
import com.mixfood.apirest.entity.User;
import com.mixfood.apirest.models.services.SocialNetworkService;
import com.mixfood.apirest.models.services.UserService;
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
@RequestMapping("/api/")
public class SocialNetworkRestController
{
    @Autowired
    private SocialNetworkService socialNetworkService;
    @Autowired
    private UserService userService;
    @GetMapping("socialnetworks")
    public List<SocialNetwork> index()
    {
       return socialNetworkService.findAll();
    }

    //*Url route
    @GetMapping("/socialnetworks/user/{id}")
    public List<SocialNetwork> showByUser(@PathVariable int id)
    {
        User user = userService.findById(id);
        return socialNetworkService.findbyIdUser(user);
    }

}
