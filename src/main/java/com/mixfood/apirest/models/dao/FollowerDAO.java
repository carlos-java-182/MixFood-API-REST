package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Follower;
import com.mixfood.apirest.projections.FollowerCard;
import com.mixfood.apirest.projections.FollowerId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import java.util.List;

public interface FollowerDAO extends CrudRepository<Follower,Integer> {

    @Query("SELECT f FROM Follower f WHERE user_id = :id")
    public Page<FollowerCard> findFollowersByIdUser(int id, Pageable pageable);

    @Query("SELECT f FROM Follower f WHERE user_id = :idUser AND follower_id = :idFollower")
    public FollowerId findByIdUserAndIdFollower(int idUser, int idFollower);
}
