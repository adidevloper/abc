package com.example.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.blog.Dto.CategoryDto;

@Service
public interface CategoryService {

	    //create
		CategoryDto createCategory(CategoryDto categoryDto);
		
		//update
		CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
		
		//delete
		void deleteCategory(Integer categoryId);
		
		//getbyId
		CategoryDto getCategory(Integer categoryId);
		
		//getAll
		List<CategoryDto> getCategories();
}
