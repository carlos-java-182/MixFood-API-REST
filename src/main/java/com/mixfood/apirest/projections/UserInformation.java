package com.mixfood.apirest.projections;

import com.mixfood.apirest.entity.User;

import java.util.Date;

public interface UserInformation
{
    int setId();
    int getId();
    String setName();
    String getName();
    String setLastname();
    String getLastname();
    User.Gender setGender();
    User.Gender getGender();
    Date setDateBirth();
    Date getDateBirth();
    String setCountry();
    String getCountry();
    String setDescription();
    String getDescription();
}
