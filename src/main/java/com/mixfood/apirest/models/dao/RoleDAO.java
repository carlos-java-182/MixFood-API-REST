package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoleDAO  extends CrudRepository<Role,Integer>
{
    @Query("SELECT r FROM Role r WHERE type = :type")
    public Role findRolesByType(String type);
}

