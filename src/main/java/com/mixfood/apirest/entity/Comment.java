package com.mixfood.apirest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "comments")
public class Comment 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idComment")
	private int id;
	@NotEmpty
	@Column(nullable = false)
	private String content;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	@PrePersist
	public void prePersist()
	{
		createAt = new Date();
	}
	@Temporal(TemporalType.DATE)
	private Date updateAt;

	//@JsonIgnore
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	/*@OneToOne(mappedBy = "comment",fetch = FetchType.LAZY)
	private Ranking ranking;*/


}
