package com.example.blog.controller;

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

import com.example.blog.Dto.ApiResponse;
import com.example.blog.Dto.CategoryDto;
import com.example.blog.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createCategory=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
		
    }
	
	@PutMapping("/update/{catId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable("catId") Integer catId){
		CategoryDto updateCategory=this.categoryService.updateCategory(categoryDto, catId);
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
		
    }
	

	@DeleteMapping("/delete/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("catId") Integer catId){
	      this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted",true),HttpStatus.OK);
		
    }
	
	@GetMapping("/getbyid/{catId}")
	public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("catId") Integer catId){
	    CategoryDto categoryGet= this.categoryService.getCategory(catId);
		return new ResponseEntity<CategoryDto>(categoryGet,HttpStatus.OK);
		
    }
	
	@GetMapping("/getall")
	public ResponseEntity<List<CategoryDto>> getCategoryAll(){
	    List<CategoryDto> categoryGetAll= this.categoryService.getCategories();
	    	return ResponseEntity.ok(categoryGetAll);
		
		
    }
	
}
