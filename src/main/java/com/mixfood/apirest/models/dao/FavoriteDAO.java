package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Favorite;
import com.mixfood.apirest.projections.FavoriteCard;
import com.mixfood.apirest.projections.FavoriteId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavoriteDAO extends CrudRepository<Favorite,Integer>
{
    @Query("SELECT f FROM Favorite f WHERE user_id = :id")
    public Page<FavoriteCard> findAllByIdUser(int id, Pageable pageable);

    @Query("SELECT f FROM Favorite f WHERE user_id = :idUser AND recipe_id = :idRecipe")
    public FavoriteId findIdbyIdUserAndIdRecipe(int idUser, int idRecipe);

}
