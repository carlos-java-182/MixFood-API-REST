package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Image;
import com.mixfood.apirest.projections.ImagePrincipal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ImageDAO extends CrudRepository<Image,Integer>
{
   // @Query("SELECT i FROM Image i WHERE i.principal = 1 AND recipe_id =:id")
   // public ImagePrincipal findPrincipalById(int id);

}
