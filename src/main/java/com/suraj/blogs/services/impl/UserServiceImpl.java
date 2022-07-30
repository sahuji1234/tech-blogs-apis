package com.suraj.blogs.services.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suraj.blogs.entities.User;
import com.suraj.blogs.payloads.UserDto;
import com.suraj.blogs.repositories.UserRepo;
import com.suraj.blogs.services.UserService;
import com.suraj.blogs.exceptions.*;

@Service
public class UserServiceImpl implements UserService{
  
	
	@Autowired
	private UserRepo userRepo;
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
		
	    User savedUser = this.userRepo.save(user);
			
		return this.userToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		user.setName(userDto.getName());
		user.setAddress(userDto.getAddress());
		user.setAlternateAddress(userDto.getAlternateAddress());
		user.setState(userDto.getState());
		user.setZip(userDto.getZip());
		user.setCity(userDto.getCity());
		User updatedUser= this.userRepo.save(user);
		return this.userToUserDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "Id", userId));
		return this.userToUserDto(user);

	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users= this.userRepo.findAll();	
	    List<UserDto> userDto =	users.stream().map(user -> this.userToUserDto(user)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","id",userId));
		this.userRepo.deleteById(userId);
	}
	
	
	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		
//		User user = new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAddress(userDto.getAddress());
//		user.setAlternateAddress(userDto.getAlternateAddress());
//		user.setState(userDto.getState());
//		user.setCity(userDto.getCity());
//		user.setZip(userDto.getZip());
//		user.setPhone(user.getPhone());
		return user;
	}

	
	public UserDto userToUserDto(User user) {
		
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
//		UserDto userDto = new UserDto();		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAddress(user.getAddress());
//		userDto.setAlternateAddress(user.getAlternateAddress());
//		userDto.setState(user.getState());
//		userDto.setCity(user.getCity());
//		userDto.setZip(user.getZip());
//		userDto.setPhone(user.getPhone());
		
		
		return userDto;
	}
	
}
