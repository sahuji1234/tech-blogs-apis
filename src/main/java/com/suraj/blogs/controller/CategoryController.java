package com.suraj.blogs.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.suraj.blogs.payloads.ApiResponse;
import com.suraj.blogs.payloads.CategoryDto;
import com.suraj.blogs.services.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

 @Autowired
 private CategoryServiceImpl catServiceImpl;
 
 
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto catDto){
	  CategoryDto createdCategory = this.catServiceImpl.createCategory(catDto);
	 
	  return new ResponseEntity<CategoryDto>(createdCategory,HttpStatus.CREATED);
	 
    }
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto catDto,@PathVariable("carId") Integer cid){
		CategoryDto updatedCategory = this.catServiceImpl.updateCategory(catDto, cid);
		return new ResponseEntity<CategoryDto>(updatedCategory,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponse> deletedCategory(@PathVariable("catId") Integer cid){
		this.catServiceImpl.deleteCategory(cid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category isdeleted successfully", true),HttpStatus.OK);	
	}
	
	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("catId") Integer cid){
	CategoryDto catDto =	this.catServiceImpl.getCategory(cid);
	return new ResponseEntity<CategoryDto>(catDto,HttpStatus.OK);
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		List<CategoryDto> categories = this.catServiceImpl.getCategories();		
		return new ResponseEntity<List<CategoryDto>>(categories, HttpStatus.OK);
		
	}
	
}
