package com.example.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.blog.Exception.ResourceNotFoundExcpetion;
import com.example.blog.entity.User;
import com.example.blog.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loading user from database by username
				User user = this.userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundExcpetion("User","email :"+username,0));
						
				System.out.println(user);
				return  user;
	}

}
