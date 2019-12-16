package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Category;

import java.util.List;

public interface CategoryService
{
    public List<Category> findAll();
    public Category findById(int id);
    public Category save(Category category);
    public void delete(int id);
}
