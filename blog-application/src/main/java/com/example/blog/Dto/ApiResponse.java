package com.example.blog.Dto;

import lombok.Data;

@Data
public class ApiResponse {

	private String message;
	private boolean success;
	
	public ApiResponse(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}
	
	
}
