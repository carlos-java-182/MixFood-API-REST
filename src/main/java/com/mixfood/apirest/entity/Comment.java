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

@Entity
@Table(name = "comments")
public class Comment 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTag")
	private int id;
	@NotEmpty
	@Column(nullable = false)
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	@Temporal(TemporalType.DATE)
	private Date updateAt;
	
}
