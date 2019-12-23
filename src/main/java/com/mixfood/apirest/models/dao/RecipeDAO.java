package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Recipe;
import com.mixfood.apirest.projections.RecipeCard;
import com.mixfood.apirest.projections.RecipeLatest;
import com.mixfood.apirest.projections.RecipeSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeDAO extends CrudRepository<Recipe,Integer>
{
   /* @Query(value =  "SELECT r.id_recipe AS id, r.name AS name, r.create_at AS createAt, r.averange_ranking AS averangeRanking, r.thumb_route AS thumbRoute, r.user_id AS idUser, CONCAT(u.name,' ', u.lastname) AS username, r.category_id AS idCategory, c.name AS categoryName FROM Recipes r \n" +
            "INNER JOIN users u ON u.id_user = r.user_id " +
            "INNER JOIN categories c ON r.category_id = c.id_category ORDER BY r.create_at DESC LIMIT 10",
            nativeQuery = true)*/
    //@Query("SELECT r.id_recipe AS id, r.name AS name, r.create_at AS createAt, r.averange_ranking AS averangeRanking, r.thumb_route AS thumbRoute, r.user_id AS idUser FROM Recipes r ")
//    public List<RecipeCard> findAllForCards();

    //*Get Recients recipes
    @Query("SELECT r FROM Recipe r WHERE Status = 'public' ORDER BY createAt DESC")
    public List<RecipeCard> findAllForCards(Pageable pageable);

    //*Find recipes by name
    @Query(value = "SELECT id_recipe AS id, name FROM recipes WHERE name LIKE :term%", nativeQuery = true)
    public List<RecipeSearch> findLikeName(String term);

    //*Find recents recipes by id user

    @Query("SELECT r FROM Recipe r WHERE Status = 'public' AND user_id = :id ORDER BY createAt DESC")
    public List<RecipeLatest> findRecentsByIdUser(int id, Pageable pageable);

    @Query("SELECT r FROM Recipe r WHERE Status = 'public' AND user_id = :id ORDER BY averangeRanking DESC")
    public List<RecipeCard> findCardsByAverangeRankingAndIdUser(int id, Pageable pageable);


}
