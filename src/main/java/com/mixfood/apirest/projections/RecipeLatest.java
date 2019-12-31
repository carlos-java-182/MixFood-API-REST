package com.mixfood.apirest.projections;

import java.util.Date;

public interface RecipeLatest
{
    int getId();
    int getAverangeRanking();
    int getTotalLikes();
    String getName();
    String getDescription();
    String getThumbRoute();
    Date getCreateAt();

    Category getCategory();
    User getUser();

    interface Category{
        int getId();
        String getName();
    }

    interface  User{
        int getId();
        String getName();
        String getLastname();
    }
}
