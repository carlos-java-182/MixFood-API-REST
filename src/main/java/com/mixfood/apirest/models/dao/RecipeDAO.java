package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Recipe;
import com.mixfood.apirest.projections.RecipeCard;
import com.mixfood.apirest.projections.RecipeSearch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeDAO extends CrudRepository<Recipe,Integer>
{
    @Query(value =  "SELECT r.id_recipe AS id, r.name AS name, r.create_at AS createAt, r.averange_ranking AS averangeRanking, r.thumb_route AS thumbRoute, r.user_id AS idUser, CONCAT(u.name,' ', u.lastname) AS username, r.category_id AS idCategory, c.name AS categoryName FROM Recipes r \n" +
            "INNER JOIN users u ON u.id_user = r.user_id " +
            "INNER JOIN categories c ON r.category_id = c.id_category ORDER BY r.create_at DESC LIMIT 10",
            nativeQuery = true)
    public List<RecipeCard> findAllForCards();

    @Query(value = "SELECT id_recipe AS id, name FROM recipes WHERE name LIKE :term%", nativeQuery = true)
    public List<RecipeSearch> findLikeName(String term);

}
