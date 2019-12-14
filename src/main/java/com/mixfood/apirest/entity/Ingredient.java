package com.mixfood.apirest.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="ingredients")
public class Ingredient 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idIngredient")
	private int id;
	
	@NotEmpty
	@Size(max = 60)
	@Column(nullable = false, unique = true)
	private String name;
	@NotEmpty
	@Size(max = 80)
	@Column(nullable = false, unique = true)
	private String thumbRoute;
	@Temporal(TemporalType.DATE)
	private Date createAt;
	@Temporal(TemporalType.DATE)
	private Date updateAt;
	
	//*Create aux table for relationship many to many to recipes
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "Recipes_Ingredients",
			joinColumns = { @JoinColumn(name="recipes_id") },
			inverseJoinColumns = { @JoinColumn(name="ingredients_id") }
			)
	private List<Recipe> recipes = new ArrayList<>();
}
