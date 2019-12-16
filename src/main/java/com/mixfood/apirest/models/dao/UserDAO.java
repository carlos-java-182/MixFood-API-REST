package com.mixfood.apirest.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mixfood.apirest.entity.User;

public interface UserDAO extends CrudRepository<User,Integer> 
{
    //*
    /*@Query("SELECT u FROM users u WHERE u.email = ?1")
    public User findByEmail();*/

}
