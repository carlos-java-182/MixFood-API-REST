package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Follower;
import com.mixfood.apirest.models.dao.FollowerDAO;
import com.mixfood.apirest.projections.FollowerCard;
import com.mixfood.apirest.projections.FollowerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FollowerServiceImpl implements FollowerService
{
    @Autowired
    private FollowerDAO followerDAO;


    @Override
    @Transactional(readOnly = true)
    public Page<FollowerCard> findFollowersByIdUser(int id, Pageable pageable) {
        return followerDAO.findFollowersByIdUser(id, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Follower> findAll() {
        return (List<Follower>) followerDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Follower findById(int id) {
        return  followerDAO.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Follower save(Follower follower) {
        return followerDAO.save(follower);
    }

    @Override
    @Transactional
    public void delete(int id) {
        followerDAO.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public FollowerId findByIdUserAndIdFollower(int idUser, int idFollower) {
        return  followerDAO.findByIdUserAndIdFollower(idUser,idFollower);
    }
}
