package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Ranking;

import java.util.List;

public interface RankingService
{
    public List<Ranking> findAll();
    public Ranking findById(int id);
    public Ranking save(Ranking ranking);
    public void delete(int id);
}
