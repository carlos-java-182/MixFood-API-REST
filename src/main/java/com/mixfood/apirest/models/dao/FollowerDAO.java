package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Follower;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FollowerDAO extends CrudRepository<Follower,Integer> {

    @Query(value = "SELECT * FROM followers WHERE user_id = :id",
    nativeQuery = true)
    public List<Follower> findFollowersByIdUser(int id);
}
