package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Ranking;
import com.mixfood.apirest.projections.RankingComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RankingDAO extends CrudRepository<Ranking, Integer>
{

    @Query("SELECT r FROM Ranking r WHERE recipe_id =:id")
    public Page<RankingComment> findByIdRecipe(int id, Pageable pageable);

    //*Search ranking by id recipe and id user
    @Query("SELECT r FROM Ranking r WHERE recipe_id =:idRecipe AND user_id =:idUser")
    public Ranking findByIdRecipeAndIdUser(int idRecipe, int idUser);

}
