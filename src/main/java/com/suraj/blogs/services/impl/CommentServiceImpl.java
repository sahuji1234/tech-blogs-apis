package com.suraj.blogs.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suraj.blogs.entities.Comment;
import com.suraj.blogs.entities.Post;
import com.suraj.blogs.entities.User;
import com.suraj.blogs.exceptions.ResourceNotFoundException;
import com.suraj.blogs.payloads.CommentsDto;
import com.suraj.blogs.repositories.CommentRepo;
import com.suraj.blogs.repositories.PostRepo;
import com.suraj.blogs.repositories.UserRepo;
import com.suraj.blogs.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentsDto createComment(CommentsDto commentDto, Integer PostId, Integer UserId) {
		Post post = this.postRepo.findById(PostId).orElseThrow(()-> new ResourceNotFoundException("post", "id", PostId));
		User user = this.userRepo.findById(UserId).orElseThrow(()-> new ResourceNotFoundException("user", "id", UserId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		comment.setUser_comment(user);
		Comment saveComment = this.commentRepo.save(comment);	
		return this.modelMapper.map(saveComment, CommentsDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "id", commentId));
		this.commentRepo.deleteById(commentId);
		
	}

	@Override
	public List<CommentsDto> getALlComments() {
		List<Comment> comments = this.commentRepo.findAll();
		List<CommentsDto> dtos = comments.stream().map((comment)->this.modelMapper.map(comment,CommentsDto.class)).collect(Collectors.toList());
		return dtos;
	}

	

}
