package com.example.blog.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	
	@Column(name = "title",length=100,nullable=false)
	private String categoryTitle;
	
	@Column(name = "description")
	private String categoryDescription;
	
	@OneToMany(mappedBy =  "category" ,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Post> posts=new ArrayList<>();

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryTitle=" + categoryTitle + ", categoryDescription="
				+ categoryDescription + ", posts=" + posts + "]";
	}

	 
}
