package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Category;
import com.mixfood.apirest.models.dao.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    @Transactional
    public List<Category> findAll() {
        return (List<Category>) categoryDAO.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryDAO.findById(id).orElse(null);
    }

    @Override
    public Category save(Category category) {
        return categoryDAO.save(category);
    }

    @Override
    public void delete(int id) {
        categoryDAO.deleteById(id);
    }
}
