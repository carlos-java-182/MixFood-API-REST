package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Category;
import com.mixfood.apirest.projections.CategoryCard;
import com.mixfood.apirest.projections.CategoryList;
import com.mixfood.apirest.projections.CategoryName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryDAO extends CrudRepository<Category,Integer>
{
    @Query(value =  "SELECT id_category AS id, name, amount_recipes AS amountRecipes, thumb_route AS thumbRoute FROM categories",
            nativeQuery = true)
    public List<CategoryCard> findAllForCards();

    @Query("SELECT c FROM Category c ORDER BY amountRecipes DESC")
    public Page<CategoryList> findListLimit(Pageable pageable);

    @Query("SELECT c FROM Category c ORDER BY name")
    public List<CategoryList> findAllList();

    @Query(value = "SELECT DISTINCT c.id_category AS id, c.name AS name, amount_recipes AS amountRecipes FROM categories c INNER JOIN recipes r ON c.id_category = r.category_id WHERE r.user_id =:id",
            nativeQuery = true)
    public List<CategoryList> findListByIdUser(int id, Pageable pageable);

    @Query("SELECT c FROM Category c WHERE c.id = :id")
    public CategoryName findNameById(int id);

    @Query("SELECT c FROM Category c ORDER BY amountRecipes DESC")
    public Page<CategoryCard> findAllCards(Pageable pageable);




}
