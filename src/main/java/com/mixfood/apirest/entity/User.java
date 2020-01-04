package com.mixfood.apirest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User implements Serializable
{
	public User()
	{

	}
    private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idUser")
	private int id;
	@NotEmpty
	@Size(max = 45)
	@Column(nullable = false)
	private String name;
	@NotEmpty
	@Size(max = 45)
	@Column(nullable = false)
	private String lastname;
	@NotEmpty
	@Email
	@Column(nullable = false, unique = true)
	private String email;
	@NotEmpty
	@Column(length = 60)
	private String password;
	@NotEmpty
	@Size(max = 2)
	@Column(nullable = false)
	private String age;
	//@NotEmpty
	@Column(length = 6)
	@Enumerated(EnumType.STRING)
	private Gender gender;
  //  @Enumerated(EnumType.STRING)
	private String status;
	@NotEmpty
	@Size(max = 80)
	@Column(name = "porfileimage_route")
	private String porfileimageRoute;
	@NotEmpty
	//text
	private String description;
	//@NotEmpty
	@Type(type = "date")
	@Temporal(TemporalType.DATE)
	private Date dateBirth;
	@NotEmpty
	private String country;
	//@NotEmpty
	@Size(max = 200)
	private String token;
	@Temporal(TemporalType.DATE)
	private Date createAt;
	@PrePersist
	public void prePersist()
	{
		createAt = new Date();
	}
    @Temporal(TemporalType.DATE)
	private Date updateAt;

    
   //@OneToMany(targetEntity = Recipe.class, cascade = CascadeType.ALL, mappedBy = "user")
	//*Relationship one to many to recipes
    //@JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
    private List<Recipe> recipes = new ArrayList<Recipe>();

	//*Relationship one to many to followers (user to follower)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private List<Follower> users = new ArrayList<>();

	//*Relationship one to many to followers (follower to user)
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "follower_id")
	private List<Follower> followers = new ArrayList<>();

	//*Relationship many to one to categories
	/*@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id",nullable = false)
	private Role role;*/

	@OneToMany(mappedBy = "user")
	private List<Ranking> rankings = new ArrayList<>();

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToMany(mappedBy = "user")
	private List<SocialNetwork> socialNetworks = new ArrayList<>();

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToMany(mappedBy = "usersLike")
	private List<Recipe> recipesLike = new ArrayList<>();

	public enum Gender
	{
		MALE,
		FEMALE,
		OTHER
	}

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

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPorfileimageRoute() {
		return porfileimageRoute;
	}

	public void setPorfileimageRoute(String porfileimageRoute) {
		this.porfileimageRoute = porfileimageRoute;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	/*
public List<Recipe> getRecipes() {
	return recipes;
}*/



public void setRecipes(List<Recipe> recipes) {
	this.recipes = recipes;
}

/*public List<Recipe> getRecipes() {
	return recipes;
}

public void setRecipes(List<Recipe> recipes) {
	this.recipes = recipes;
}*/

	/*public List<Ranking> getRankings() {
		return rankings;
	}  */

	/*public List<Follower> getFollowers() {
		return followers;
	}
*/
	public void setFollowers(List<Follower> followers) {
		this.followers = followers;
	}

	/*public List<Follower> getUsers() {
		return users;
	}*/

	public void setUsers(List<Follower> users) {
		this.users = users;
	}

	public void setRankings(List<Ranking> rankings) {
		this.rankings = rankings;
	}

	public List<SocialNetwork> getSocialNetworks() {
		return socialNetworks;
	}

	public void setSocialNetworks(List<SocialNetwork> socialNetworks) {
		this.socialNetworks = socialNetworks;
	}



	public void setRecipesLike(List<Recipe> recipesLike) {
		this.recipesLike = recipesLike;
	}
}
