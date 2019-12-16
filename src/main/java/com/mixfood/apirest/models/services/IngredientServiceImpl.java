package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Ingredient;
import com.mixfood.apirest.models.dao.IngredientDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService
{
    @Autowired
    private IngredientDAO ingredientDAO;


    @Override
    @Transactional
    public List<Ingredient> findAll()
    {
        return (List<Ingredient>) ingredientDAO.findAll();
    }

    @Override
    /**
     * @id: id ingredient for find it in database
     */
    public Ingredient findById(int id)
    {
        return ingredientDAO.findById(id).orElse(null);
    }

    @Override
    public Ingredient save(Ingredient ingredient)
    {
        return ingredientDAO.save(ingredient);
    }

    @Override
    public void delete(int id)
    {
        ingredientDAO.deleteById(id);
    }
}
