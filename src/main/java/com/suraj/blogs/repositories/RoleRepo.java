package com.suraj.blogs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suraj.blogs.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
