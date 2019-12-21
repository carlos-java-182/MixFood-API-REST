package com.mixfood.apirest.projections;

import java.util.Date;

public interface RecipeCard {
    int getId();
    int getAverangeRanking();
    int getIdUser();
    String getName();
    String getThumbRoute();
    String getUserName();
    String getCategoryName();
    Date getCreateAt();
}
