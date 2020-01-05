package com.mixfood.apirest.projections;

import java.util.Date;

public interface RankingComment
{
    int getId();
    int getPunctuation();
    String getComment();
    User getUser();
    Date getCreateAt();
    interface User
    {
        int getId();
        String getName();
        String getLastname();
        String getPorfileimageRoute();
    }

}
