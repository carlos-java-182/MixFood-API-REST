package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.projections.UserEmail;
import com.mixfood.apirest.projections.UserInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mixfood.apirest.entity.User;

public interface UserDAO extends CrudRepository<User,Integer> 
{
    //*
    @Query("SELECT u FROM User u WHERE u.email =:email")
    public User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE id = :id")
    public UserInformation findInformationById(int id);

    @Query("SELECT u FROM User u WHERE id = :id")
    public UserEmail findEmailById(int id);

    @Query("SELECT u FROM User u WHERE enabled =:enabled")
    public Page<User> findAllPaginate(Pageable pageable,Boolean enabled);

    @Query("SELECT u FROM User u WHERE name LIKE :term% AND enabled = :enabled")
    public Page<User> findPaginateByLikeName(String term, Boolean enabled, Pageable pageable);






}
