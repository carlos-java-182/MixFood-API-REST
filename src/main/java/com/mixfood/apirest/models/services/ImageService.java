package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Image;

import java.util.List;

public interface ImageService
{
    public List<Image> findAll();
    public Image findById(int id);
    public Image save(Image image);
    public void delete(int id);
}
