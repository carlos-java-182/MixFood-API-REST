package com.mixfood.apirest.entity;

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

	@OneToOne(mappedBy = "comment")
	private Ranking ranking;
}
