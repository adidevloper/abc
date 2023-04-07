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
import com.example.blog.Dto.UserDto;
import com.example.blog.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser( @Valid @RequestBody UserDto userDto){
		UserDto createUserDto = userService.createUser(userDto);
	    return new ResponseEntity<>(createUserDto,HttpStatus.OK);
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") int userId)
	{
		UserDto updateUser = this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updateUser);
     }
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId)
	{
		userService.deleteUser(userId);
		//return ResponseEntity.ok(Map.of("message","User Deleted Succesfully"));
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Succesfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		return ResponseEntity.ok(this.userService.getAllUsers());
	}

	@GetMapping("/findid/{userId}")
	public ResponseEntity<UserDto> getSingleUsers(@PathVariable Integer userId)
	{
 	    UserDto userById = userService.getUserById(userId);
		return ResponseEntity.ok(userById);
		
	}
}
