package com.suraj.blogs.payloads;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.suraj.blogs.entities.Comment;
import com.suraj.blogs.entities.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;	
	private String name;
	private String email;
	private String password;
	private String address;
	private String alternateAddress;
	private String city;
	private String state;
	private int zip;
	private Long phone;
	
	private Set<CommentsDto> comments = new HashSet<>();
	
	private Set<RoleDto> roles = new HashSet<>();
	
	@JsonIgnore
	public String getPassword()
	{
		return this.password;
	}	
	
	@JsonProperty
	public void setPassword(String password)
	{
		this.password = password;
	}
	
}
