package com.example.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.blog.security.CustomUserDetailService;

@Configuration
@EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailService customUserDetailService;
	

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
		 .csrf()
		 .disable()
		 .authorizeRequests()
		 .antMatchers("/").
		 hasAnyRole("user","admin","HR")
		 .anyRequest().authenticated()
		 .and()
		 .httpBasic().disable();
//		 .formLogin().permitAll();
//		 .and()
//		 .httpBasic();
//		 .authenticationEntryPoint();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncoder());
	}
	
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
		
		DaoAuthenticationProvider authProvider= new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(this.customUserDetailService);
		
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
		
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		 
		return super.authenticationManagerBean();
	}

}
