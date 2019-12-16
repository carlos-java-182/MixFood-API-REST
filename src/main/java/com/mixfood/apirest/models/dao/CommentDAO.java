package com.mixfood.apirest.models.dao;

import com.mixfood.apirest.entity.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentDAO  extends CrudRepository<Comment,Integer> {
}
