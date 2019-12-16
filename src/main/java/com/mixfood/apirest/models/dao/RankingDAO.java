package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Ranking;
import org.springframework.data.repository.CrudRepository;

public interface RankingDAO extends CrudRepository<Ranking,Integer>
{
}
