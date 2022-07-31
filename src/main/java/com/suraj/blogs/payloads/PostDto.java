package com.suraj.blogs.payloads;

import java.util.Date;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {


private String title;
private String content;
private String imageName;
private Date addedDate;
private UserDto user;
private CategoryDto category;

	
}
