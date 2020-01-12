package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Role;
import com.mixfood.apirest.models.dao.RoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService
{
    @Autowired
    RoleDAO roleDAO;


    @Override

    @Transactional(readOnly = true)
    public Role findRolesByType(String type) {
        return roleDAO.findRolesByType(type);
    }
}
