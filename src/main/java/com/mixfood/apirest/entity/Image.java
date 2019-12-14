package com.mixfood.apirest.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="images")
public class Image
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idImage")
	private int id;
	@NotEmpty
	@Size(max = 80)
	@Column(nullable = false)
	private String routeImage;
	@Temporal(TemporalType.DATE)
	private Date createAt;
	@Temporal(TemporalType.DATE)
	private Date updateAt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="recipe_id", nullable = false)
	private Recipe recipe;
	
	
}
