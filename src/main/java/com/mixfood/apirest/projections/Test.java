package com.mixfood.apirest.projections;

import java.util.Date;
import java.util.List;

public interface Test {
    int getId();
    int getAverangeRanking();
    int getViews();
    String getName();
    String getThumbRoute();
    Date getCreateAt();
    Category getCategory();
    User getUser();


    List<Tag> getTags();

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
