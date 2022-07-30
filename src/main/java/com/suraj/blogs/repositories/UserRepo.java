package com.suraj.blogs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suraj.blogs.entities.User;


@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
