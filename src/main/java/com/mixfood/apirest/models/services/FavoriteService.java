package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Favorite;
import com.mixfood.apirest.projections.FavoriteId;

import java.util.List;

public interface FavoriteService
{
    public List<Favorite> findAllByIdUser(int id);
    public Favorite save(Favorite favorite);
    public void delete(int id);
    public FavoriteId findIdbyIdUserAndIdRecipe(int idUser, int idRecipe);
}
