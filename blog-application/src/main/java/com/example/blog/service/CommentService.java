package com.example.blog.service;

import org.springframework.stereotype.Service;

import com.example.blog.Dto.CommentDto;

@Service
public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId);
	
	void deleteComment(Integer commentId);
}
