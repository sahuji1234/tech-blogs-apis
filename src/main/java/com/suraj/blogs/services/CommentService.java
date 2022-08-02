package com.suraj.blogs.services;

import java.util.List;

import com.suraj.blogs.payloads.CommentsDto;

public interface CommentService {

	CommentsDto createComment(CommentsDto commentDto,Integer PostId,Integer UserId);
	void deleteComment(Integer commentId);
	List<CommentsDto> getALlComments();
	
}
