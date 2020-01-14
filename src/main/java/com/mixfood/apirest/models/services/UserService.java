package com.mixfood.apirest.models.services;

import java.util.List;

import com.mixfood.apirest.entity.User;
import com.mixfood.apirest.projections.UserEmail;
import com.mixfood.apirest.projections.UserInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
   // public User findByEmail(String email);
   // public loadUserByUsername(String username);
    public UserInformation findInformationById(int id);

    public UserEmail findEmailById(int id);

    public User findByEmail(String email);

    public Page<User> findAllPaginate(Pageable pageable,Boolean enabled);
    public Page<User> findPaginateByLikeName(String term, Boolean enabled, Pageable pageable);

}
