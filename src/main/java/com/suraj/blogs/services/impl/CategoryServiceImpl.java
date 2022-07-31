package com.suraj.blogs.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suraj.blogs.entities.Category;
import com.suraj.blogs.exceptions.ResourceNotFoundException;
import com.suraj.blogs.payloads.CategoryDto;
import com.suraj.blogs.repositories.CategoryRepo;
import com.suraj.blogs.services.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService {

	
	@Autowired
	private CategoryRepo catRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		this.catRepo.save(cat);
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat = this.catRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","categoryId",categoryId));
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		Category updatedcat= this.catRepo.save(cat);	
		return this.modelMapper.map(updatedcat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat = this.catRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "id", categoryId));		
		this.catRepo.deleteById(categoryId);		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat = this.catRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "id", categoryId));
		
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> categories = this.catRepo.findAll();
	 List<CategoryDto> catDtos =	categories.stream().map((cat)-> this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return catDtos;
	}


	
	

}
