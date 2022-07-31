package com.suraj.blogs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="categories")
@Setter
@Getter
@NoArgsConstructor
public class Category {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	
	@Column(name="category_title",nullable = false)
	private String categoryTitle;
	
	@Column(name ="category_desc")
	private String categoryDescription;
	
	
}
