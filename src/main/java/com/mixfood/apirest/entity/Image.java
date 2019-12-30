package com.mixfood.apirest.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="images")
public class Image implements Serializable
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idImage")
	private int id;
	@NotEmpty
	@Column(nullable = false)
	private String routeImage;
	@Column(columnDefinition = "BOOLEAN default false")
	private Boolean isPrincipal;
	@Temporal(TemporalType.DATE)
	private Date createAt;
	@PrePersist
	public void prePersist()
	{
		createAt = new Date();
	}
	@Temporal(TemporalType.DATE)
	private Date updateAt;

	//*Relationship many to one recipe
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="recipe_id", nullable = false)
	private Recipe recipe;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRouteImage() {
		return routeImage;
	}

	public void setRouteImage(String routeImage) {
		this.routeImage = routeImage;
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

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Boolean getPrincipal() {
		return isPrincipal;
	}

	public void setPrincipal(Boolean principal) {
		isPrincipal = principal;
	}
}
