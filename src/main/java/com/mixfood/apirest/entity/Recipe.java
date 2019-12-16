package com.mixfood.apirest.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

@Entity
@Table(name="recipes")
public class Recipe 
{
	public Recipe()
	{

	}
	private static final long serialVersionUID = 1L;

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
	//@Enumerated(EnumType.STRING)
	private String status;
	private int averangeRanking;
	private String dificult;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	@PrePersist
	public void prePersist()
	{
		createAt = new Date();
	}
	@Temporal(TemporalType.DATE)
	private Date updateAt;

	/**
	 * Relationships
	 */

	//*Relationship many to one to user
	//@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@ManyToOne(cascade = CascadeType.ALL)
	//*Column for select join
	@JoinColumn(name="user_id",nullable = false)
	private User user;

	//*Relationship many to one to categories
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id",nullable = false)
	private Category category;

	//*Relationship many to many to ingredients
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable( name ="recipes_ingredients",
			joinColumns = { @JoinColumn(name="recipes_id") },
			inverseJoinColumns = { @JoinColumn(name="ingredients_id")}

	)
	private List<Ingredient> ingredients = new ArrayList<>();

	//*Relationship many to many to tags
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "recipes_tags",
		joinColumns = {@JoinColumn(name = "recipes_id")},
		inverseJoinColumns = {@JoinColumn(name = "tags_id")}
	)
	private List<Tag> tags = new ArrayList<>();


	//*Relationship one to many to images
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "recipe_id")
	private List<Image> images = new ArrayList<Image>();


//	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "recipes")

	
/*	//*Relationship many to many to tags
	@ManyToMany(mappedBy = "recipes")   */

	
	/*public enum Status
	{
		active,
		inactive
	}*/
	//*Getters and Setters
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

	public String getPreparationTime() {
		return preparationTime;
	}

	public void setPreparationTime(String preparationTime) {
		this.preparationTime = preparationTime;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public String getVideoRoute() {
		return videoRoute;
	}

	public void setVideoRoute(String videoRoute) {
		this.videoRoute = videoRoute;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAverangeRanking() {
		return averangeRanking;
	}

	public void setAverangeRanking(int averangeRanking) {
		this.averangeRanking = averangeRanking;
	}

	public String getDificult() {
		return dificult;
	}

	public void setDificult(String dificult) {
		this.dificult = dificult;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
}

