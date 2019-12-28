package com.mixfood.apirest.entity;

import javax.persistence.*;

@Entity
@Table(name = "likes")
public class Like
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLike")
    private int id;



}
