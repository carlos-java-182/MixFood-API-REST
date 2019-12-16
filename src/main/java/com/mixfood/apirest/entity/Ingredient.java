package com.mixfood.apirest.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="ingredients")
public class Ingredient 
{
	private static final long serialVersionUID = 1L;

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
	@PrePersist
	public void prePersist()
	{
		createAt = new Date();
	}
	@Temporal(TemporalType.DATE)
	private Date updateAt;
	
	//*Create aux table for relationship many to many to recipes
	@ManyToMany(cascade = CascadeType.ALL)
	/*@JoinTable(
			name = "Recipes_Ingredients",
			joinColumns = { @JoinColumn(name="recipes_id") },
			inverseJoinColumns = { @JoinColumn(name="ingredients_id")}

			)*/
	/*@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable( name ="recipes_ingredients",
			joinColumns = { @JoinColumn(name="ingredients_id") },
			inverseJoinColumns = { @JoinColumn(name="recipes_id")}

	)*/
	private List<Recipe> recipes = new ArrayList<>();

	/*@ManyToMany(cascade = CascadeType.ALL)
	private List<Recipe>*/
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThumbRoute() {
		return thumbRoute;
	}

	public void setThumbRoute(String thumbRoute) {
		this.thumbRoute = thumbRoute;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}
}
