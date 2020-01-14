package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Favorite;
import com.mixfood.apirest.models.dao.RecipeDAO;
import com.mixfood.apirest.projections.FavoriteCard;
import com.mixfood.apirest.projections.FavoriteId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FavoriteService
{
    public Page<FavoriteCard> findAllByIdUser(int id, Pageable pageable);
    public Favorite save(Favorite favorite);
    public Favorite findById(int id);
    public void delete(int id);
    public FavoriteId findIdbyIdUserAndIdRecipe(int idUser, int idRecipe);
}
