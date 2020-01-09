package com.mixfood.apirest.projections;

public interface FollowerCard
{
    int getId();
    FollowerUserName getFollower();

    interface FollowerUserName
    {
        int getId();
        String getName();
        String getLastname();
        String getPorfileimageRoute();

    }
}
