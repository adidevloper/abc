package com.example.blog.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class Role  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	 
// 	@JsonBackReference
//	@ManyToMany(mappedBy ="roles", cascade =  CascadeType.ALL,fetch = FetchType.LAZY)
//	private List<User> user= new ArrayList<>();

	@LazyCollection(LazyCollectionOption.FALSE)
 	@JsonBackReference
	@ManyToMany(mappedBy="roles", cascade = CascadeType.ALL)
	private List<User> user= new ArrayList<>();

	 
}

