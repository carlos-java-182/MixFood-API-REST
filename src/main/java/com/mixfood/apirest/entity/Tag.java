package com.mixfood.apirest.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
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
	private int mentions;
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
	

	
	//*Create aux table for relationship many to many to recipes\
	@JsonIgnoreProperties("tags")
	@ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
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

	public int getMentions() {
		return mentions;
	}

	public void setMentions(int mentions) {
		this.mentions = mentions;
	}
}
