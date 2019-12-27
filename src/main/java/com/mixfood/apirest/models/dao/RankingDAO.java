package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingDAO extends JpaRepository<Ranking, Integer>
{


}
