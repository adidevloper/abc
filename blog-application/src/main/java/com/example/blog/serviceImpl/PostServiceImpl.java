package com.example.blog.serviceImpl;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.blog.Dto.PostDto;
import com.example.blog.Dto.PostResponse;
import com.example.blog.Exception.ResourceNotFoundExcpetion;
import com.example.blog.entity.Category;
import com.example.blog.entity.Post;
import com.example.blog.entity.User;
import com.example.blog.repository.CategoryRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user=this.userRepository.findById(userId)
		.orElseThrow(()-> new ResourceNotFoundExcpetion("User", "User id", userId));
				// System.out.println(user);
		Category category=this.categoryRepository.findById(categoryId)
		.orElseThrow(()->new ResourceNotFoundExcpetion("Category", "Category id", categoryId));
				 
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);  

		Post newPost = this.postRepository.save(post);
        PostDto map = modelMapper.map(newPost, PostDto.class);
	    return map;
		 
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepository.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundExcpetion("Post", "PostId", postId));
				  
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post save = this.postRepository.save(post);
		return this.modelMapper.map(save, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundExcpetion("Post", "PostId", postId));
				 this.postRepository.delete(post);
	}

	@Override
	public  PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir) {
		//using ternary oprator
		//Sort sort=(sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();   
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc"))
		{	
			sort=Sort.by(sortBy).ascending();
		}else
		{
			sort=Sort.by(sortBy).descending();
		}
		 Pageable p=PageRequest.of(pageNumber, pageSize, sort);
		 Page<Post> pagePost = postRepository.findAll(p);
		 List<Post> allPost = pagePost.getContent();
		 
		List<PostDto> postDtos = allPost.stream().map(find->modelMapper.map(find,PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPaages(pagePost.getTotalPages());
		postResponse.setLastPaages(pagePost.isLast());

		return postResponse;
		 
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepository.findById(postId)
			.orElseThrow(()-> new ResourceNotFoundExcpetion("Post", "PostId", postId));
		return this.modelMapper.map(post, PostDto.class);
		
	}

	@Override
	public List<PostDto> getPostByCateory(Integer categoryId) {
		Category cat =this.categoryRepository.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundExcpetion("User", "UserId", categoryId));
	   List<Post>  posts = this.postRepository.findByCategory(cat);
       List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
      return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user =this.userRepository.findById(userId)
				.orElseThrow(()->new ResourceNotFoundExcpetion("User", "user id", userId));
        List<Post>  posts = this.postRepository.findByUser(user);
        List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

		return postDtos;	 
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		//List<Post> posts = this.postRepository.findByTitleContaining("%"+keyword+"%");
		List<Post> posts = this.postRepository.findByTitleContaining(keyword);
		List<PostDto> postDto = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

		return postDto;
	}

}
