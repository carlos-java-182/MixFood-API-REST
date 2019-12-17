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
	@OneToOne
	private User user;
	
	//*Relationship one to one to recipe
	@JoinColumn(name = "recipe_id", nullable = false)
	@OneToOne
	private Recipe recipe;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
}
