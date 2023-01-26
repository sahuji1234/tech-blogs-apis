package com.suraj.blogs.security;

import com.suraj.blogs.payloads.UserDto;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;
	
	private UserDto user;
	
}
