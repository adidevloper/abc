package com.example.blog.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="user_name",nullable =false,length =100)
	private String name;
	private String email;
	private String password;
	private String about;
	
	
	@JsonBackReference
	 @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
	private List<Post> posts=new ArrayList<>();

	 
//	 @JsonBackReference
//     @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//     @JoinTable(name = "user_role",
//     joinColumns = @JoinColumn(name="user",referencedColumnName = "id"),
//     inverseJoinColumns = @JoinColumn(name="role",referencedColumnName = "id"))
//	 private List<Role> roles=new ArrayList<>();
	 
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonBackReference
	 @ManyToMany(cascade = CascadeType.ALL)
	    private List<Role> roles=new ArrayList<>();


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 List<SimpleGrantedAuthority> authories =this.roles.stream().map((role)-> 
		 new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		 return authories; 
		 
	}


	@Override
	public String getUsername() {
		return this.email;
		 
	}


	@Override
	public boolean isAccountNonExpired() {
		 return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		 return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		 return true;
	}


	@Override
	public boolean isEnabled() {
		 return true;
	}
	    
	 
}
