package com.example.blog.Dto;

import java.util.List;

import lombok.Data;

@Data
public class PostResponse {

	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPaages;
	private boolean lastPaages;

}
