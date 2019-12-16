package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminDAO extends CrudRepository<Admin,Integer>
{
}
