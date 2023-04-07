package com.example.blog.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.Dto.CommentDto;
import com.example.blog.Exception.ResourceNotFoundExcpetion;
import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		 Post post=this.postRepository.findById(postId)
				 .orElseThrow(()->new ResourceNotFoundExcpetion("Post", "Postid",postId));
				  
			Comment	comment= this.modelMapper.map(commentDto, Comment.class);
			comment.setPost(post);
			Comment savecomment = this.commentRepository.save(comment);
			
		  return this.modelMapper.map(savecomment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundExcpetion("Comment", "commentId", commentId));
		this.commentRepository.delete(comment);
	}

}
