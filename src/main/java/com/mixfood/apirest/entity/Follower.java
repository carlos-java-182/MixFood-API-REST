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

	@PrePersist
	public void prePersist()
	{
		createAt = new Date();
	}
	@Temporal(TemporalType.DATE)
	private Date createAt;

	@Temporal(TemporalType.DATE)
	private Date updateAt;

	/*public Follower(Date createAt, Date updateAt, User user, User follower)
	{
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.user = user;
		this.follower = follower;
	}*/
	/*
	*Relationships
	 */

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "follower_id", nullable = false)
	private User follower;

	//*Getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public User getFollower() {
		return follower;
	}

	public void setFollower(User follower) {
		this.follower = follower;
	}
}
