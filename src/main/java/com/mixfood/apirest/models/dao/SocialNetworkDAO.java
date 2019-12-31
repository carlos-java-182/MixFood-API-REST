package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.SocialNetwork;
import com.mixfood.apirest.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SocialNetworkDAO extends CrudRepository<SocialNetwork,Integer>
{
    @Query("SELECT s FROM SocialNetwork s WHERE s.user = :user")
    public List<SocialNetwork> findbyIdUser(User user);

}
