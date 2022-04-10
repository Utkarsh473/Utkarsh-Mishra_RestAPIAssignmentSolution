package com.greatlearning.employeeMgmt.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name = "Users")
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "User_id")
	private int id;
	
	@Column(name = "username")
	private String username;	

	@Column(name = "password")
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable
	(
		name = "users_roles",
		joinColumns = @JoinColumn(name = "User_id"),
		inverseJoinColumns = @JoinColumn(name = "Role_id")
		
	)
	private List<Role> roles = new ArrayList<Role>();

	 
}
