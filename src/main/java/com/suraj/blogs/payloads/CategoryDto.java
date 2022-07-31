package com.suraj.blogs.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

	private Integer categoryId;
	
	@NotBlank
	@Size(min=5 , message = "title must be 5 characters long")
	private String categoryTitle;
	
	@NotBlank
	@Size(min = 10 , message= "description must be 10 characters long")
	private String categoryDescription;
	
}
