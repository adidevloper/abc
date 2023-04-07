package com.example.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.blog.Dto.CommentDto;
import com.example.blog.Dto.RoleDto;
import com.example.blog.entity.Role;
import com.example.blog.service.RoleService;

@Controller
public class RoleController {

	@Autowired
    private	RoleService roleService;
	
	
	//create
		@PostMapping("/roles/{userId}")
		public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto,@PathVariable Integer userId)
				 
		{
			RoleDto createRole=this.roleService.createRole(roleDto,userId);
			return new ResponseEntity<RoleDto>(createRole,HttpStatus.CREATED);
       }
 	 
}
