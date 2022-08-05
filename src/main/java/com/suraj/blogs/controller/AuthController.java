package com.suraj.blogs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suraj.blogs.exceptions.ApiException;
import com.suraj.blogs.payloads.JwtAuthRequest;
import com.suraj.blogs.payloads.UserDto;
import com.suraj.blogs.security.JwtAuthResponse;
import com.suraj.blogs.security.JwtTokenHelper;
import com.suraj.blogs.services.UserService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired
	private JwtTokenHelper helper;
	
	@Autowired
	private UserDetailsService service;
	
	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/login")
public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest req) throws Exception{
		
		this.authenticated(req.getUsername(),req.getPassword());
		UserDetails details = this.service.loadUserByUsername(req.getUsername());
		String generateToken = this.helper.generateToken(details);
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(generateToken);
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	
}


	private void authenticated(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,password);
			try {
				this.manager.authenticate(token);
			} catch (BadCredentialsException e) {
			  throw new ApiException("Invalid user name or password");
			}
		
		
	}
	
	
	// register new user api
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registeUser(@RequestBody UserDto user){
		UserDto registerNewUser = this.userService.registerNewUser(user);
		
		return new ResponseEntity<UserDto>(registerNewUser,HttpStatus.CREATED);
	}
	
}
