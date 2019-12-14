package com.mixfood.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.mixfood.apirest.entity.User;

public interface UserDAO extends CrudRepository<User,Integer> 
{

}
