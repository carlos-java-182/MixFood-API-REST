package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Category;
import com.mixfood.apirest.models.dao.CategoryDAO;
import com.mixfood.apirest.projections.CategoryCard;
import com.mixfood.apirest.projections.CategoryList;
import com.mixfood.apirest.projections.CategoryName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService
{
    @Autowired
    private CategoryDAO categoryDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return (List<Category>) categoryDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Category findById(int id) {
        return categoryDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Category save(Category category) {
        return categoryDAO.save(category);
    }

    @Override
    @Transactional
    public void delete(int id) {
        categoryDAO.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryCard> findAllForCards() {
        return categoryDAO.findAllForCards();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CategoryList> findListLimit(Pageable pageable) {
        return categoryDAO.findListLimit(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryList> findAllList() {
        return categoryDAO.findAllList();
    }


    @Override
    @Transactional(readOnly = true)
    public List<CategoryList> findListByIdUser(int id, Pageable pageable) {
        return categoryDAO.findListByIdUser(id,pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryName findNameById(int id) {
        return categoryDAO.findNameById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CategoryCard> findAllCards(Pageable pageable) {
        return categoryDAO.findAllCards(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Category> findAllPaginate(Pageable pageable) {
        return categoryDAO.findAllPaginate(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Category> findPaginateByLikeName(String term, Pageable pageable) {
        return categoryDAO.findPaginateByLikeName(term, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Category findByName(String name) {
        return categoryDAO.findByName(name);
    }
}
