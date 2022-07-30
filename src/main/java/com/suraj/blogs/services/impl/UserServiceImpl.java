package com.suraj.blogs.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.suraj.blogs.entities.User;
import com.suraj.blogs.payloads.UserDto;
import com.suraj.blogs.repositories.UserRepo;
import com.suraj.blogs.services.UserService;

public class UserServiceImpl implements UserService{
  
	
	@Autowired
	private UserRepo userRepo;
	
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
		
	    User savedUser = this.userRepo.save(user);
			
		return this.userToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto user, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		
	}
	
	
	public User dtoToUser(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAddress(userDto.getAddress());
		user.setAlternateAddress(userDto.getAlternateAddress());
		user.setState(userDto.getState());
		user.setCity(userDto.getCity());
		user.setZip(userDto.getZip());
		user.setPhone(user.getPhone());
		return user;
	}

	
	public UserDto userToUserDto(User user) {
		UserDto userDto = new UserDto();
		
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAddress(user.getAddress());
		userDto.setAlternateAddress(user.getAlternateAddress());
		userDto.setState(user.getState());
		userDto.setCity(user.getCity());
		userDto.setZip(user.getZip());
		userDto.setPhone(user.getPhone());
		
		
		return userDto;
	}
	
}
