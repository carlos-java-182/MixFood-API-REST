package com.mixfood.apirest.models.services;

import com.mixfood.apirest.entity.Image;
import com.mixfood.apirest.models.dao.ImageDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ImageServiceImpl implements ImageService
{
    @Autowired
    private ImageDAO imageDAO;

    @Override
    public List<Image> findAll() {
        return (List<Image>) imageDAO.findAll();
    }

    @Override
    public Image findById(int id) {
        return imageDAO.findById(id).orElse(null);
    }

    @Override
    public Image save(Image image) {
        return imageDAO.save(image);
    }

    @Override
    public void delete(int id) {
        imageDAO.deleteById(id);
    }
}
