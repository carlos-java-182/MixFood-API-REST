package com.mixfood.apirest.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="users")
public class User implements Serializable
{
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
	private String password;
	@NotEmpty
	@Size(max = 2)
	@Column(nullable = false)
	private String age;
	@NotEmpty
	private String sex;
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
	@Size(max = 200)
	private String token;
    @Temporal(TemporalType.DATE)
	private Date createAt;
    @Temporal(TemporalType.DATE)
	private Date updateAt;
    
   @OneToMany(mappedBy = "user")
    private List<Recipe> recipes = new ArrayList<>();

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

public String getSex() {
	return sex;
}

public void setSex(String sex) {
	this.sex = sex;
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

/*public List<Recipe> getRecipes() {
	return recipes;
}

public void setRecipes(List<Recipe> recipes) {
	this.recipes = recipes;
}*/
    
   
   
   
   
   }
