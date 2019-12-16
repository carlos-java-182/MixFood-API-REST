package com.mixfood.apirest.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name ="followers")
public class Follower
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idFollower")
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date createAt;
	public void prePersist()
	{
		createAt = new Date();
	}
	@Temporal(TemporalType.DATE)
	private Date updateAt;

	/*
	*Relationships
	 */

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "follower_id", nullable = false)
	private User follower;


	

}
