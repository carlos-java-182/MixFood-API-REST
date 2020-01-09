package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Follower;
import com.mixfood.apirest.projections.FollowerCard;
import com.mixfood.apirest.projections.FollowerId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FollowerService
{
    public Page<FollowerCard> findFollowersByIdUser(int id, Pageable pageable);
    public List<Follower> findAll();
    public Follower findById(int id);
    public Follower save(Follower follower);
    public void delete(int id);
    public FollowerId findByIdUserAndIdFollower(int idUser, int idFollower);

}
