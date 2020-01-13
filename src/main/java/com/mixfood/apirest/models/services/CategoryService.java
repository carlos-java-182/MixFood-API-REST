package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Category;
import com.mixfood.apirest.projections.CategoryCard;
import com.mixfood.apirest.projections.CategoryList;
import com.mixfood.apirest.projections.CategoryName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService
{
    public List<Category> findAll();
    public Category findById(int id);
    public Category save(Category category);
    public void delete(int id);
    public List<CategoryCard> findAllForCards();
    public Page<CategoryList> findListLimit(Pageable pageable);
    public List<CategoryList> findAllList();
    public List<CategoryList> findListByIdUser(int id, Pageable pageable);
    public CategoryName findNameById(int id);
    public Page<CategoryCard> findAllCards(Pageable pageable);
    public Page<Category> findAllPaginate(Pageable pageable);
    public Page<Category> findPaginateByLikeName(String term, Pageable pageable);
    public Category findByName(String name);


}
