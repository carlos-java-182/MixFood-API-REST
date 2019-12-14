package com.mixfood.apirest.models.services;

import java.util.List;

import com.mixfood.apirest.entity.User;

public interface UserService 
{
	//*
	public List<User> findAll();
    //*
	public User findById(int id);
    //*
    public User save(User user);
    //*
    public void delete(int id);
    //*
    public boolean emailisValid(String email);
    //*
    public boolean authisValid(String email, String password);
    //*
    

}
