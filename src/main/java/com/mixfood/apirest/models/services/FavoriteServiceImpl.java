package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Favorite;
import com.mixfood.apirest.models.dao.FavoriteDAO;
import com.mixfood.apirest.projections.FavoriteId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService
{
    @Autowired
    private FavoriteDAO favoriteDAO;

    @Override
    public List<Favorite> findAllByIdUser(int id) {
        return (List<Favorite>) favoriteDAO.findAllByIdUser(id);
    }

    @Override
    public Favorite save(Favorite favorite) {
        return favoriteDAO.save(favorite);
    }

    @Override
    public void delete(int id) {
        favoriteDAO.deleteById(id);
    }

    @Override
    public FavoriteId findIdbyIdUserAndIdRecipe(int idUser, int idRecipe) {
        return favoriteDAO.findIdbyIdUserAndIdRecipe(idUser,idRecipe);
    }


}
