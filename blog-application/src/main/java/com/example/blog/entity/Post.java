package com.example.blog.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data 
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name="post_title",length=100,nullable = false)
	private String title;
	
	@Column(length = 100)
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	

	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY) 
	private User user;
	
	@JsonBackReference
	@ManyToOne (cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@JoinColumn(name="category_id")
	private Category category;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private List<Comment> comments=new ArrayList<Comment>();
}
