package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Category;
import com.mixfood.apirest.projections.CategoryCard;
import com.mixfood.apirest.projections.CategoryList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryDAO extends CrudRepository<Category,Integer>
{
    @Query(value =  "SELECT id_category AS id, name, amount_recipes AS amountRecipes, thumb_route AS thumbRoute FROM categories",
            nativeQuery = true)
    public List<CategoryCard> findAllForCards();

    @Query("SELECT c FROM Category c ORDER BY amountRecipes DESC")
    public  List<CategoryList> findAllForList();

    //@Query("SELECT c FROM Category c")
    //public List<CategoryCard> findAll();

}
