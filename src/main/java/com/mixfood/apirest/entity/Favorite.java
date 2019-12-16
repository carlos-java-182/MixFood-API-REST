package com.mixfood.apirest.entity;

import java.util.Date;

import javax.persistence.*;

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
	@PrePersist
	public void prePersist()
	{
		createAt = new Date();
	}
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
