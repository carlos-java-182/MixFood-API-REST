package com.mixfood.apirest.entity;

import java.io.Serializable;
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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="tags")
public class Tag implements Serializable
{
	//*Attributes
	@Id
	//*Generate Autoincrement
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//*Column name
	@Column(name="idTag")
	private int id;
	@NotEmpty
	//*Column size
	@Size(max = 45)
	@Column(nullable = false, unique = true)
	private String name;
	@Column(nullable = false)
	private String thumbRoute;
	@Column(name="create_at", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date createAt;
	@PrePersist
	public void prePersist()
	{
		createAt = new Date();
	}
	@Column(name="update_at")
	@Temporal(TemporalType.DATE)
	private Date updateAt;
	

	
	//*Create aux table for relationship many to many to recipes
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Recipe> recipes = new ArrayList<>();

	//*Getters and setters
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
