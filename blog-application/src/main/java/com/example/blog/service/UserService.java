package com.example.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.blog.Dto.UserDto;

@Service
public interface UserService {

	//UserDto registerNewUser(UserDto user);

	UserDto createUser(UserDto user);

	UserDto updateUser(UserDto user, Integer userId);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers(); 

	void deleteUser(Integer userId);

}
