package com.suraj.blogs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suraj.blogs.entities.Category;
import com.suraj.blogs.entities.Post;
import com.suraj.blogs.entities.User;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {

	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);
	
	
}
