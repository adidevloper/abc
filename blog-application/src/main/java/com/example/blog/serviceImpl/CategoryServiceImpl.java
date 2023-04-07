package com.example.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.Dto.CategoryDto;
import com.example.blog.Exception.ResourceNotFoundExcpetion;
import com.example.blog.entity.Category;
import com.example.blog.repository.CategoryRepository;
import com.example.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.modelMapper.map(categoryDto, Category.class);
		  Category addcat = this.categoryRepo.save(cat);
		return this.modelMapper.map(addcat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
 		 Category cat=this.categoryRepo.findById(categoryId)
 				 .orElseThrow(()-> new ResourceNotFoundExcpetion("Category","category Id", categoryId));
 	   
 		 cat.setCategoryTitle(categoryDto.getCategoryTitle());
 	     cat.setCategoryDescription(categoryDto.getCategoryDescription());
 		 Category updatedcat= this.categoryRepo.save(cat);
 		 
		return this.modelMapper.map(updatedcat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		 Category cat = this.categoryRepo.findById(categoryId)
				 .orElseThrow(()-> new ResourceNotFoundExcpetion("Category", "category id", categoryId));
				  
		 this.categoryRepo.delete(cat);

	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category cat= this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundExcpetion("category", "Category Id", categoryId));
			 
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
	 List<Category> cat=this.categoryRepo.findAll();
	 List<CategoryDto> catdto = cat.stream().map((category)->this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
	return catdto;
	}

}
