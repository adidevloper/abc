package com.example.blog.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.Dto.CommentDto;
import com.example.blog.Dto.RoleDto;
import com.example.blog.Exception.ResourceNotFoundExcpetion;
import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.entity.Role;
import com.example.blog.entity.User;
import com.example.blog.repository.RoleRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
	
	
	@Override
	public RoleDto createRole(RoleDto roleDto, Integer userId) {
		User user = userRepository.findById(userId)
		.orElseThrow(()->new ResourceNotFoundExcpetion("User", "UserId",userId));
		 Role role = modelMapper.map(roleDto, Role.class);
		// role.setUser1(user);
		 Role save = roleRepository.save(role);
		 RoleDto roleToDto = modelMapper.map(save, RoleDto.class);
//		Role role1=new Role();
//		List<User> users= new ArrayList<>();
//		users.add(user);
//		role.setUser(users);n   
//		Role save = roleRepository.save(role);
		return roleToDto;
	}

	 
	
	 


}