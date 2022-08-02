package com.suraj.blogs.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.suraj.blogs.entities.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

private Integer postId;
private String title;
private String content;
private String imageName;
private Date addedDate;
private UserDto user;
private CategoryDto category;

private Set<CommentsDto> comments = new HashSet<>();

	
}
