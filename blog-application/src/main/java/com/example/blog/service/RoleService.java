package com.example.blog.service;

import org.springframework.stereotype.Service;

import com.example.blog.Dto.RoleDto;
import com.example.blog.entity.Role;

@Service
public interface RoleService {

	//create post
  RoleDto createRole(RoleDto roleDto, Integer userId);
}
