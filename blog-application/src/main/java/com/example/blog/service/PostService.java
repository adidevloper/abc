package com.example.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.blog.Dto.PostDto;
import com.example.blog.Dto.PostResponse;

@Service
public interface PostService {

	//create post
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	//update post
	PostDto updatePost(PostDto postDto,Integer postId);
	
	//delete post
	void  deletePost(Integer postId);
	
	//get all post
	PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
	
	//get single post by Id
	PostDto getPostById(Integer categoryId);  
	
	//get all post by category 
    List<PostDto> getPostByCateory(Integer categoryId);
    
    //get all post by user 
    List<PostDto> getPostByUser(Integer userId);

    //search post
    List<PostDto> searchPosts(String keyword);

}
