package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Favorite;
import java.util.List;

public interface FavoriteService
{
    public List<Favorite> findAllByIdUser(int id);
    public Favorite save(Favorite favorite);
    public void delete(int id);
}
