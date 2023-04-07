package com.example.blog.Dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CategoryDto {

private Integer categoryId;
	
	@NotBlank(message = "title is not blank")	
	@Size(min=4,message = "minimum size is 4")
	private String categoryTitle;
	
	@NotBlank(message = "description is not blank")
	@Size(min = 4,message = "minimum size is 10")
	private String categoryDescription;

	@Override
	public String toString() {
		return "CategoryDto [categoryId=" + categoryId + ", categoryTitle=" + categoryTitle + ", categoryDescription="
				+ categoryDescription + "]";
	}

}
