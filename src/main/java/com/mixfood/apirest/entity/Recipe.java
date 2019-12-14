package com.mixfood.apirest.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

@Entity
@Table(name="recipes")
public class Recipe 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idRecipe")
	private int id;
	@NotEmpty
	//*Colum size
	@Size(max = 45)
	@Column(nullable = false, unique = true)
	private String name;
	@Size(max = 5)
	@Column(nullable = false)
	private String preparationTime;
	@Column(length = 655535, columnDefinition = "TEXT")
	@Type(type="text")
	private String steps;
	@Size(max = 80)
	private String videoRoute;
	@Enumerated(EnumType.STRING)
	private Status status;
	private int averangeRanking;
	private String dificult;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	@Temporal(TemporalType.DATE)
	private Date updateAt;
	

	//*Relationship many to one to user
	@ManyToOne(fetch = FetchType.LAZY)
	//Column for select join
	@JoinColumn(name="user_id",nullable = false)
	private User user;
	
	//*Relationship many to many to ingredients
	@ManyToMany(mappedBy = "recipes")
	private List<Ingredient> ingredients = new ArrayList<>();
	
	//*Relationship many to many to tags
	@ManyToMany(mappedBy = "recipes")
	private List<Tag> tags = new ArrayList<>();
	
	public enum Status
	{
		active,
		inactive
	}
	
	
}

