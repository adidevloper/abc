package com.example.blog.Dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.blog.entity.Role;

import lombok.Data;

@Data
public class UserDto {

	private int id;
	
    @NotEmpty
	@Size(min=4,message = "username must be min  of 4 characters")
	private String name;
    
    @Email(message = "email is not valid")
	private String email;
    
    @NotNull
 	@Size(min=3,max=10,message = "password must be min 3 character and max 10 ")
	private String password;
    
	@NotNull
	private String about;

	private List<RoleDto> roles ;
}
