package com.mixfood.apirest.controllers;

import ch.qos.logback.core.rolling.helper.FileNamePattern;
import com.mixfood.apirest.entity.Image;
import com.mixfood.apirest.entity.Recipe;
import com.mixfood.apirest.entity.User;
import com.mixfood.apirest.models.services.ImageService;
import com.mixfood.apirest.models.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import javax.validation.Valid;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

//*Set cross origin for angular server
@CrossOrigin(origins = {"http://localhost:4200"})
//*
@RestController
//*Path mapping
@RequestMapping("/api/")
public class ImageRestController
{
    @Autowired
    private ImageService imageService;
    @Autowired
    private RecipeService recipeService;

    /**
     **This function
     * @param images
     * @param result
     * @return
     */
    @PostMapping("images")
    public ResponseEntity<?> create(@Valid @RequestBody List<Image> images, BindingResult result)
    {
        //*Objects declaration
        Image newImage = null;
        Map<String,Object> response = new HashMap<>();

        //*Validate errors
        if(result.hasErrors())
        {
            //*List declaration
            List<String> errors = new ArrayList<>();
            //*Get errors and add to list
            for(FieldError err: result.getFieldErrors())
            {
                errors.add("Field '"+err.getField()+"' "+err.getDefaultMessage());
            }
            //*Add errors list to response map
            response.put("errors", errors);
            //*Return response
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try
        {
            //*Save images in database and add image in the object
            for(Image image: images)
            {
                imageService.save(image);
            }
           // newImage = imageService.save(image);
        }
        catch(DataAccessException e)
        {
            //*Response database error
            response.put("message","Error inserting into database");
            response.put("error",e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //*Created image response
        response.put("message", "The image has been created");
        response.put("image",images);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }

    /**
     *
     * @param files
     * @param id
     * @param principalImage
     * @return
     */
    @PostMapping("images/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("images")MultipartFile[] files, @RequestParam("id") int id, @RequestParam("principalImage") String principalImage)
    {
        //*Object declaration
        Map<String,Object> response = new HashMap<>();

        if (files.length > 0)
        {
            //*Find recipe
            Recipe recipe = recipeService.findById(id);
            for (MultipartFile file: files)
            {
                //*Get file type
                String fileType = "."+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
                //*Create unique file name
                String fileName = "RecipeImage_"+UUID.randomUUID().toString()+fileType;
                //*Create file route
                Path fileRoute = Paths.get("uploads/recipes").resolve(fileName).toAbsolutePath();

                try {
                    Files.copy(file.getInputStream(),fileRoute);
                } catch (IOException e) {
                    //*Response database error
                    response.put("message","Error updating image from database!");
                    response.put("error",e.getMessage().concat(" : ").concat(e.getCause().getMessage()));
                    return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }

                //*Create Object image
                Image image = new Image();
                if(principalImage.equals(file.getOriginalFilename()))
                {

                    image.setPrincipal(true);
                }
                image.setRouteImage(fileName);
                image.setRecipe(recipe);

                //*Create image in database
                imageService.save(image);
            }
            response.put("message","upload success!");
        }
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    @GetMapping("uploads/recipes/{imageName:.+}")
    public ResponseEntity<Resource> showImage(@PathVariable String imageName)
    {
        Path fileRoute = Paths.get("uploads/recipes").resolve(imageName).toAbsolutePath();
        Resource resource = null;

        try
        {
            resource = new UrlResource((fileRoute.toUri()));
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

        if(!((UrlResource) resource).exists() && !((UrlResource) resource).isReadable())
        {
            throw new RuntimeException("Error to load image: "+imageName);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" +((UrlResource) resource).getFilename() + "\"");

        return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
    }

    @GetMapping("uploads/{imageName:.+}/type/{type}")
    public ResponseEntity<Resource> showImageUser(@PathVariable String imageName, @PathVariable typeImage type)
    {
        Path fileRoute = null;
        //*Validate get type image
        if(type == typeImage.RECIPE)
        {
            fileRoute = Paths.get("uploads/recipes").resolve(imageName).toAbsolutePath();
        }
        else if(type == typeImage.USER)
        {
            fileRoute = Paths.get("uploads/users").resolve(imageName).toAbsolutePath();
        }
        else if(type == typeImage.CATEGORY)
        {
            fileRoute = Paths.get("uploads/categories").resolve(imageName).toAbsolutePath();
        }

        Resource resource = null;

        try
        {
            resource = new UrlResource((fileRoute.toUri()));
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

        if(!((UrlResource) resource).exists() && !((UrlResource) resource).isReadable())
        {
            throw new RuntimeException("Error to load image: "+imageName);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" +((UrlResource) resource).getFilename() + "\"");

        return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
    }

    public enum typeImage
    {
        USER,
        RECIPE,
        CATEGORY
    }

}
