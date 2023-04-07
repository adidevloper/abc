package com.example.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.Dto.ApiResponse;
import com.example.blog.Dto.CommentDto;
import com.example.blog.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;


	@PostMapping("/comments/{postId}")
	public ResponseEntity<CommentDto> createcomment(@RequestBody CommentDto commentDto,@PathVariable Integer postId){
		CommentDto createComment = this.commentService.createComment(commentDto, postId);

		return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);

	}

	@DeleteMapping("/delete/{commentId}")
	public ResponseEntity<ApiResponse> deletecomment(@PathVariable Integer commentId){
		this.commentService.deleteComment(commentId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted succesfully",true),HttpStatus.CREATED);

	}
	
}
