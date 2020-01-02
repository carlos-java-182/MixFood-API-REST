package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Follower;
import com.mixfood.apirest.models.dao.FollowerDAO;
import com.mixfood.apirest.projections.FollowerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FollowerServiceImpl implements FollowerService
{
    @Autowired
    private FollowerDAO followerDAO;

    @Override
    public List<Follower> findFollowersByIdUser(int id) {
        return (List<Follower>) followerDAO.findFollowersByIdUser(id);
    }

    @Override
    @Transactional
    public List<Follower> findAll() {
        return (List<Follower>) followerDAO.findAll();
    }

    @Override
    public Follower findById(int id) {
        return  followerDAO.findById(id).orElse(null);
    }

    @Override
    public Follower save(Follower follower) {
        return followerDAO.save(follower);
    }

    @Override
    public void delete(int id) {
        followerDAO.deleteById(id);
    }

    @Override
    public FollowerId findByIdUserAndIdFollower(int idUser, int idFollower) {
        return  followerDAO.findByIdUserAndIdFollower(idUser,idFollower);
    }
}
