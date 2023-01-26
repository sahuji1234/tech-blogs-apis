package com.suraj.blogs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suraj.blogs.payloads.ApiResponse;
import com.suraj.blogs.payloads.CommentsDto;
import com.suraj.blogs.services.impl.CommentServiceImpl;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {

	@Autowired
	private CommentServiceImpl commentService;
	
	
	@PostMapping("post/{postId}/user/{userId}")
	public ResponseEntity<CommentsDto> createComment(@RequestBody CommentsDto comment,@PathVariable Integer postId,@PathVariable Integer userId){
		CommentsDto createComment = this.commentService.createComment(comment, postId, userId);
		
		return new ResponseEntity<CommentsDto>(createComment,HttpStatus.CREATED);
		
	}
	
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("deleted successfully",true),HttpStatus.OK);
		
	}
	
	@GetMapping("")
	public ResponseEntity<List<CommentsDto>> getAllComments(){
		 List<CommentsDto> allComments = this.commentService.getALlComments();	
		return new ResponseEntity<List<CommentsDto>>(allComments,HttpStatus.OK);
		
	}
	

	
}
