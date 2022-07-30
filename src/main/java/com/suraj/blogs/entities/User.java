package com.suraj.blogs.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty
	@Size(min =4, message ="user name must be minimum of 4 characters")
	private String name;
	
	@Email(message = "your given email address is not valid")
	private String email;
	
	@NotEmpty
	@Size(min = 8, max =16 ,message = "password must not be empty and must be between 8 And 16 characters long")
	@Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])(?=.{8,})")
	private String password;
	
	@NotEmpty(message = "address must not be blank")
	private String address;
	private String alternateAddress;
	private String city;
	private String state;
	private int zip;
	
	@NotNull
	private Long phone;
	
}
