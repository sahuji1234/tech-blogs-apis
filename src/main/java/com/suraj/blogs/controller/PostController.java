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
import com.suraj.blogs.payloads.PostResponse;
import com.suraj.blogs.services.impl.PostServiceImpl;
import com.suraj.blogs.utils.AppConstants;

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
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value="pageNumber" , defaultValue = AppConstants.PAGE_NUMBER , required = false) Integer pageNumber ,
			@RequestParam(value="pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false)Integer pageSize,
	 		@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
	 		@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
	    )
	{
		 PostResponse posts = this.postServiceImpl.getAllPosts(pageNumber ,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);	
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
	
	
	@GetMapping("posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> getBySearchedTitle(@PathVariable("keywords") String keyword){
	List<PostDto> dtos = this.postServiceImpl.searchPosts(keyword);
    return new ResponseEntity<List<PostDto>>(dtos,HttpStatus.OK);
		
	}
}
