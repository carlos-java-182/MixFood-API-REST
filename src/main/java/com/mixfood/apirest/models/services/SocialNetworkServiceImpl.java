package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.SocialNetwork;
import com.mixfood.apirest.entity.User;
import com.mixfood.apirest.models.dao.SocialNetworkDAO;
import com.mixfood.apirest.projections.SocialNetworkList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialNetworkServiceImpl implements SocialNetworkService
{
    @Autowired
    private SocialNetworkDAO socialNetworkDAO;


    @Override
    public List<SocialNetwork> findAll() {
        return (List<SocialNetwork>) socialNetworkDAO.findAll();
    }

    @Override
    public SocialNetwork findById(int id) {
        return socialNetworkDAO.findById(id).orElse(null);
    }

    @Override
    public SocialNetwork save(SocialNetwork socialNetwork) {
        return socialNetworkDAO.save(socialNetwork);
    }

    @Override
    public void delete(int id) {
        socialNetworkDAO.deleteById(id);
    }

    @Override
    public List<SocialNetwork> findbyIdUser(User user) {
        return socialNetworkDAO.findbyIdUser(user);
    }

    @Override
    public List<SocialNetworkList> findListbyId(int id) {
        return socialNetworkDAO.findListbyId(id);
    }

    @Override
    public SocialNetwork findByIdUserAndNetwork(int id, SocialNetwork.Network network) {
        return socialNetworkDAO.findByIdUserAndNetwork(id, network);
    }
}
