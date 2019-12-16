package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageDAO extends CrudRepository<Image,Integer> {
}
