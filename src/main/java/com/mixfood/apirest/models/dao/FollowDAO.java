package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Follower;
import org.springframework.data.repository.CrudRepository;

public interface FollowDAO  extends CrudRepository<Follower,Integer> {

}
