package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Follower;
import com.mixfood.apirest.projections.FollowerId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FollowerDAO extends CrudRepository<Follower,Integer> {

    @Query("SELECT f FROM Follower f WHERE user_id = :id")
    public List<Follower> findFollowersByIdUser(int id);

    @Query("SELECT f FROM Follower f WHERE user_id = :idUser AND follower_id = :idFollower")
    public FollowerId findByIdUserAndIdFollower(int idUser, int idFollower);
}
