package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Ranking;
import com.mixfood.apirest.models.dao.RankingDAO;
import com.mixfood.apirest.projections.RankingComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RankingServiceImpl implements  RankingService
{
    @Autowired
    private RankingDAO rankingDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Ranking> findAll() {
        return (List<Ranking>) rankingDAO.findAll();
    }

    @Override
    public Ranking findById(int id) {
        return rankingDAO.findById(id).orElse(null);
    }

    @Override
    public Ranking save(Ranking ranking) {
        return rankingDAO.save(ranking);
    }

    @Override
    public void delete(int id) {
        rankingDAO.deleteById(id);
    }

    @Override
    public Page<RankingComment> findByIdRecipe(int id, Pageable pageable) {
        return rankingDAO.findByIdRecipe(id, pageable);
    }
}
