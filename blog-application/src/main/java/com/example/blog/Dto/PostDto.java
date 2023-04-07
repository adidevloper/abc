package com.example.blog.Dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.blog.entity.Category;
import com.example.blog.entity.Comment;
import com.example.blog.entity.User;

import lombok.Data;

@Data
public class PostDto {

	private Integer postId;
	private String title;
	private String content;
    private String imageName;
	private Date addedDate;
	
	private UserDto user;
	
	private CategoryDto category;
 	 
	private List<CommentDto> comments=new ArrayList<CommentDto>();
}
