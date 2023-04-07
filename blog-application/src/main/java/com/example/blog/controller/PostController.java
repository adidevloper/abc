package com.example.blog.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.blog.Dto.ApiResponse;
import com.example.blog.Dto.PostDto;
import com.example.blog.Dto.PostResponse;
import com.example.blog.config.AppConstant;
import com.example.blog.service.FileService;
import com.example.blog.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	//create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId)
	{
		PostDto createPost=this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);

	}
	// update post	
	@PutMapping("/updatepost/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);

	}

	//get post By Id
	@GetMapping("/postbyId/{postId}")
	public ResponseEntity<PostDto> getAllPost(@PathVariable Integer postId){
		PostDto postDto = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);

	}
	//get all posts	
	@GetMapping("/allposts")
	public ResponseEntity<PostResponse> allPost(
			@RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false)Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue =AppConstant.PAGE_SIZE,required = false)Integer pageSize,
			@RequestParam(value ="sortBy",defaultValue =AppConstant.SORT_BY,required = false) String sortBy,
			@RequestParam(value ="sortDir",defaultValue =AppConstant.SORT_DIR,required = false) String sortDir
			){
		PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);

	}
	// delete post
	@DeleteMapping("/delete/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);

		return new ApiResponse("Post is successfully deleted !!",true);
	}

	//get by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity <List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId)

	{
		List<PostDto> posts= this.postService.getPostByCateory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);

	}

	//get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity <List<PostDto>> getPostsByUser(@PathVariable Integer userId)

	{
		List<PostDto> posts= this.postService.getPostByUser(userId);	
		return new ResponseEntity<List<PostDto>> (posts,HttpStatus.OK);

	}

	//search
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword){
		List<PostDto> result = this.postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);

	}

	//post image upload
	@PostMapping("/imageupload/image/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image")MultipartFile image,
			@PathVariable Integer postId) throws IOException{

		PostDto postDto = this.postService.getPostById(postId);
		String filename = this.fileService.uploadImage(path, image);

		postDto.setImageName(filename);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
    }
	
	//method to serve files
			@GetMapping(value="/serveimage/{imagename}",produces = MediaType.IMAGE_JPEG_VALUE)
			public void downloadimage (@PathVariable ("imagename") String imagename ,HttpServletResponse response) throws IOException{
			 InputStream resource = this.fileService.getresource(path, imagename);
			  response.setContentType(MediaType.IMAGE_JPEG_VALUE);
			  StreamUtils.copy(resource, response.getOutputStream());
			}
		}
