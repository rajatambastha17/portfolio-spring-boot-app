package com.project.portfolio.service;

import java.util.List;

import com.project.portfolio.model.request.BlogPostRequest;
import com.project.portfolio.model.response.BlogPostResponse;

public interface BlogPostService {
	
	List<BlogPostResponse> getAllPosts();
    BlogPostResponse getPostById(Long id);
    BlogPostResponse createPost(BlogPostRequest request);
}
