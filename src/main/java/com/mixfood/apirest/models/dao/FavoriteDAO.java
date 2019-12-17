package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Favorite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavoriteDAO extends CrudRepository<Favorite,Integer>
{
    @Query(value =  "SELECT * FROM favorites  WHERE user_id = :id",
    nativeQuery = true)
    public List<Favorite> findAllByIdUser(int id);

}
