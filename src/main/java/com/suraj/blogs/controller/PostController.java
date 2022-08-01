package com.suraj.blogs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suraj.blogs.payloads.ApiResponse;
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
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable("userId") Integer userId){
		List<PostDto> posts = this.postServiceImpl.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("categoryId") Integer cid){
		List<PostDto> posts = this.postServiceImpl.getPostByCategory(cid);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
		
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts(@RequestParam(value="pageNumber" , defaultValue = "1" , required = false) Integer pageNumber ,@RequestParam(value="pageSize", defaultValue = "10", required = false)Integer pageSize){
		List<PostDto> posts = this.postServiceImpl.getAllPosts(pageNumber ,pageSize);
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);	
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId){
		PostDto post = this.postServiceImpl.getPostById(postId);
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}
	
	
	@PutMapping("posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto ,@PathVariable("postId") Integer postId){
		PostDto post = this.postServiceImpl.UpdatePost(postDto, postId);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
	
	@DeleteMapping("/{postId}")
	public ApiResponse deletePostById(@PathVariable("postId") Integer postId) {
		this.postServiceImpl.deletePost(postId);
		return new ApiResponse("post deleted Successfull" ,false);
	}
	
	
}
