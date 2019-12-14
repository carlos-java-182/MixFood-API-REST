package com.mixfood.apirest.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="categories")
public class Category
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotEmpty
	@Size(max = 45)
	@Column(nullable = false, unique = true)
	private String name;
	@Temporal(TemporalType.DATE)
	private Date createAt;
	@Temporal(TemporalType.DATE)
	private Date updateAt;
}
