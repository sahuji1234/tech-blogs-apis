package com.suraj.blogs.payloads;

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
	
}