package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Favorite;
import com.mixfood.apirest.models.dao.FavoriteDAO;
import com.mixfood.apirest.models.dao.RecipeDAO;
import com.mixfood.apirest.projections.FavoriteCard;
import com.mixfood.apirest.projections.FavoriteId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService
{
    @Autowired
    private FavoriteDAO favoriteDAO;

    @Override
    @Transactional(readOnly = true)
    public Page<FavoriteCard> findAllByIdUser(int id, Pageable pageable)
    {
        return favoriteDAO.findAllByIdUser(id,pageable);
    }

    @Override
    @Transactional
    public Favorite save(Favorite favorite) {
        return favoriteDAO.save(favorite);
    }

    @Override
    @Transactional
    public void delete(int id) {
        favoriteDAO.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public FavoriteId findIdbyIdUserAndIdRecipe(int idUser, int idRecipe) {
        return favoriteDAO.findIdbyIdUserAndIdRecipe(idUser,idRecipe);
    }

    @Override
    @Transactional(readOnly = true)
    public Favorite findById(int id) {
        return favoriteDAO.findById(id).orElse(null);
    }
}
