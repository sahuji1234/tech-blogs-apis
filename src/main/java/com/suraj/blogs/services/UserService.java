package com.suraj.blogs.services;

import java.util.List;

import com.suraj.blogs.payloads.JwtAuthRequest;
import com.suraj.blogs.payloads.UserDto;

public interface UserService {
	UserDto registerNewUser(UserDto user);
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	JwtAuthRequest updatePassword(JwtAuthRequest user);
	
}
