package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Role;

public interface RoleService
{
    public Role findRolesByType(String type);
}
