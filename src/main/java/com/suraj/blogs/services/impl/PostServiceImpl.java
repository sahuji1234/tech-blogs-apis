package com.suraj.blogs.services.impl;

import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.suraj.blogs.entities.Category;
import com.suraj.blogs.entities.Post;
import com.suraj.blogs.entities.User;
import com.suraj.blogs.exceptions.ResourceNotFoundException;
import com.suraj.blogs.payloads.PostDto;
import com.suraj.blogs.payloads.PostResponse;
import com.suraj.blogs.repositories.CategoryRepo;
import com.suraj.blogs.repositories.PostRepo;
import com.suraj.blogs.repositories.UserRepo;
import com.suraj.blogs.services.PostService;


@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo catRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer catId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		Category category = this.catRepo.findById(catId).orElseThrow(()-> new ResourceNotFoundException("category", "Id", catId));		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
	    post.setUser(user);
	    post.setCategory(category);
	    Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto UpdatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));
	    post.setTitle(postDto.getTitle());
	    post.setContent(postDto.getContent());
	    post.setImageName(postDto.getImageName());    
	    Post updatePost =this.postRepo.save(post);
		return this.modelMapper.map(updatePost,PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
	Post post =	this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));
		this.postRepo.deleteById(post.getPostId());
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber ,Integer pageSize,String sortBy,String sortDir) {
		 
		Sort sort = null;
	//	Sort sort =(sortDir.equalsIgnoreCase("asc")) ?Sort.by(sortDir).ascending():Sort.by(sortDir).descending();
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortDir).ascending();
		}else {
			sort = Sort.by(sortDir).descending();
		}
		
		PageRequest p = PageRequest.of(pageNumber, pageSize,sort);
				
	    Page<Post> pagePosts= this.postRepo.findAll(p);
	    List<Post> posts=  pagePosts.getContent();
	
	
	    List<PostDto> dtos = posts.stream().map(post -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());	
	   
	    PostResponse postResponse = new PostResponse();
	    postResponse.setContent(dtos);
	    postResponse.setPageNumber(pagePosts.getNumber());
	    postResponse.setPageSize(pagePosts.getSize());
	    postResponse.setTotalElements(pagePosts.getTotalElements());
	    postResponse.setTotalPages(pagePosts.getTotalPages());
	    postResponse.setLastPage(pagePosts.isLast());
	    
	    return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).get();
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category cat = catRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "id", categoryId));		
	    List<Post> posts=	this.postRepo.findByCategory(cat);
	    List<PostDto> dtos = posts.stream().map(post-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return dtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "user id ", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> dtos = posts.stream().map(post -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());	
		return dtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
      List<Post> posts = this.postRepo.findByTitleContaining(keyword);
     List<PostDto> dtos = posts.stream().map(post-> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return dtos;
	}

}
