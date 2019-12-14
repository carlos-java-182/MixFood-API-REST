package com.mixfood.apirest.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "favorites")
public class Favorite 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idFavorite")
	private int id;
	@Temporal(TemporalType.DATE)
	private Date createAt;
	@Temporal(TemporalType.DATE)
	private Date updateAt;
	
	//*Relationship one to one to user
	@JoinColumn(name = "user_id", nullable = false)
	@OneToOne(cascade = CascadeType.ALL)
	private User user;
	
	//*Relationship one to one to recipe
	@JoinColumn(name = "recipe_id", nullable = false)
	@OneToOne(cascade = CascadeType.ALL)
	private Recipe recipe;
	
	
	
}
