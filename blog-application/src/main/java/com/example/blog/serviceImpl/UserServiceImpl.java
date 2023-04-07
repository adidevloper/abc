package com.example.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.Dto.UserDto;
import com.example.blog.entity.User;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.UserService;
import com.example.blog.Exception.ResourceNotFoundExcpetion;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = modelMapper.map(userDto,User.class);
		User saveUser =userRepository.save(user);
		 return modelMapper.map(saveUser, UserDto.class); 
	} 
		 
  @Override
	public UserDto updateUser(UserDto userdto, Integer userId) {
		  User user=this.userRepository.findById(userId)
		       .orElseThrow(()-> new ResourceNotFoundExcpetion("User","Id",userId));
					  
				user.setName(userdto.getName());
				user.setEmail(userdto.getEmail());
				user.setPassword(userdto.getPassword());
				user.setAbout(userdto.getAbout());
				User updateUser = this.userRepository.save(user);
				
		  return modelMapper.map(updateUser, UserDto.class);
		}
	
  @Override
	public UserDto getUserById(Integer userId) {
		     User user=userRepository.findById(userId)
					 .orElseThrow(()-> new ResourceNotFoundExcpetion("User","Id",userId));
			 UserDto userDto = modelMapper.map(user, UserDto.class);
			 return userDto;
	     }

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users= userRepository.findAll();
		List<UserDto> userDtos =users.stream().map(user ->modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
			return userDtos;
		}

	@Override
	public void deleteUser(Integer userId) {
		User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundExcpetion("User","Id",userId));
	}
	
	
}
