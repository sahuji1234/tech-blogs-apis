package com.suraj.blogs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suraj.blogs.entities.Post;
import com.suraj.blogs.payloads.PostDto;
import com.suraj.blogs.services.impl.PostServiceImpl;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostServiceImpl postServiceImpl;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
	PostDto createdPost = this.postServiceImpl.createPost(postDto, userId, categoryId);
	return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
		
	}
	
	
	
}
