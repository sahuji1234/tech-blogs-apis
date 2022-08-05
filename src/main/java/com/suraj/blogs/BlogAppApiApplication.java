package com.suraj.blogs;


import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.suraj.blogs.entities.Role;
import com.suraj.blogs.repositories.RoleRepo;
import com.suraj.blogs.utils.AppConstants;

@SpringBootApplication
public class BlogAppApiApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("xyz"));
		
		try {
			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER_ROLE);
			role.setName("ADMIN_USER");
			
			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER_ROLE);
			role1.setName("NORMAL_USER");
			
			List<Role> roles = List.of(role,role1);		
		    List<Role> result =	this.roleRepo.saveAll(roles);
		    
		    result.forEach(r-> System.out.println(r.getName()));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	
	
}
