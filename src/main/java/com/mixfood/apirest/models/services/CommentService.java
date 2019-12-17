package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Comment;

import java.util.List;

public interface CommentService
{
    public List<Comment> findAll();
    public Comment findById(int id);
    public Comment save(Comment comment);
    public void delete(int id);
}
