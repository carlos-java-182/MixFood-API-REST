package com.mixfood.apirest.projections;

import java.util.Date;
import java.util.List;

public interface RecipeCard {
    int getId();
    int getAverangeRanking();
    int getViews();
    int getTotalLikes();
    String getName();
    String getThumbRoute();
    Date getCreateAt();
    Test.Category getCategory();
    Test.User getUser();
    List<Test.Tag> getTags();

    interface User{
        int getId();
        String getName();
        String getLastname();
    }

    interface  Category{
        int getId();
        String getName();
    }

    interface Tag{
        int getId();
        String getName();
    }
}
