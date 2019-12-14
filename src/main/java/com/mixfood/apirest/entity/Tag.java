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
	//*Colum size
	@Size(max = 45)
	@Column(nullable = false, unique = true)
	private String name;
	@Column(name="create_at", nullable = false)
	private Date createAt;
	@Column(name="update_at")
	@Temporal(TemporalType.DATE)
	private Date updateAt;
	
	/*@PrePersist
	public void prePersist()
	{
		updateAt = new Date();
	}*/
	
	//*Create aux table for relationship many to many to recipes
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "Recipes_Tags",
			joinColumns = { @JoinColumn(name="recipes_id") },
			inverseJoinColumns = { @JoinColumn(name="tags_id") }
			)
	private List<Recipe> recipes = new ArrayList<>();



}
