package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Ingredient;
import com.mixfood.apirest.models.dao.IngredientDAO;
import javafx.scene.control.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService
{
    @Autowired
    private IngredientDAO ingredientDAO;


    @Override
    @Transactional(readOnly = true)
    public List<Ingredient> findAll()
    {
        return (List<Ingredient>) ingredientDAO.findAll();
    }

    @Override
    /**
     * @id: id ingredient for find it in database
     */
    @Transactional(readOnly = true)
    public Ingredient findById(int id)
    {
        return ingredientDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Ingredient save(Ingredient ingredient)
    {
        return ingredientDAO.save(ingredient);
    }

    @Override
    @Transactional
    public void delete(int id)
    {
        ingredientDAO.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Ingredient> findAllPaginate(Pageable pageable) {
        return ingredientDAO.findAllPaginate(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Ingredient> findPaginateByLikeName(String term, Pageable pageable) {
        return ingredientDAO.findPaginateByLikeName(term,pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Ingredient findByName(String name) {
        return ingredientDAO.findByName(name);
    }
}
