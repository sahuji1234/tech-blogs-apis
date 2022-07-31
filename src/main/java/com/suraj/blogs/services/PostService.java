package com.suraj.blogs.services;

import java.util.List;


import com.suraj.blogs.entities.Post;
import com.suraj.blogs.payloads.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto,Integer userId,Integer catId);
	
	Post UpdatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	List<Post> getAllPosts();
	
	Post getPostById(Integer postId);
	
	List<Post> getPostByCategory(Integer categoryId);
	
	List<Post> getPostByUser(Integer userId);
	
	List<Post> searchPosts(String keyword);
	
}
