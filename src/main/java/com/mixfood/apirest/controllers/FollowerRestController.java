package com.mixfood.apirest.controllers;

import com.mixfood.apirest.entity.Follower;
import com.mixfood.apirest.models.services.FollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//*Set cross origin for angular server
@CrossOrigin(origins = {"http://localhost:4200"})
//*
@RestController
//*Path mapping
@RequestMapping("/api")
public class FollowerRestController
{
    @Autowired
    //*Object declaration
    private FollowerService followerService;

    //*Url route
    @GetMapping("/followers/{id}")
    public List<Follower> index(@PathVariable int id)
    {
        return followerService.findFollowersByIdUser(id);
    }
}
