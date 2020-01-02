package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Follower;
import com.mixfood.apirest.projections.FollowerId;

import java.util.List;

public interface FollowerService
{
    public List<Follower> findFollowersByIdUser(int id);
    public List<Follower> findAll();
    public Follower findById(int id);
    public Follower save(Follower follower);
    public void delete(int id);
    public FollowerId findByIdUserAndIdFollower(int idUser, int idFollower);

}
