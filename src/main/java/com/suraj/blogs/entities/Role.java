package com.suraj.blogs.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="roles")
@Getter
@Setter
public class Role {
     
	
	@Id
	private int id;
	
	private String name;
	
//	@ManyToMany
//	private Set<User> user = new HashSet<>();
	
}
