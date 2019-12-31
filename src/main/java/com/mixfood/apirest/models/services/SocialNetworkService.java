package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.SocialNetwork;
import com.mixfood.apirest.entity.User;

import java.util.List;

public interface SocialNetworkService
{
    public List<SocialNetwork> findAll();
    public SocialNetwork findById(int id);
    public SocialNetwork save(SocialNetwork socialNetwork);
    public void delete(int id);
    public List<SocialNetwork> findbyIdUser(User user);
}
