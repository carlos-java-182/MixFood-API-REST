package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AuthDAO extends CrudRepository<User,Integer>
{
    @Query( value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    public User findByEmail(String email);

}
