package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Ranking;
import com.mixfood.apirest.projections.RankingComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RankingService
{
    public List<Ranking> findAll();
    public Ranking findById(int id);
    public Ranking save(Ranking ranking);
    public void delete(int id);
    @Transactional(readOnly = true)
    public Page<RankingComment> findByIdRecipe(int id, Pageable pageable);
}
