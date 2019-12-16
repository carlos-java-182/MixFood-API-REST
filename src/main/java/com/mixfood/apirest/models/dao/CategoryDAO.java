package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryDAO extends CrudRepository<Category,Integer>
{

}
