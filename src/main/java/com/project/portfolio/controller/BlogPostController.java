package com.project.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.portfolio.model.request.BlogPostRequest;
import com.project.portfolio.model.response.BlogPostResponse;
import com.project.portfolio.service.BlogPostService;
import com.project.portfolio.util.Constants;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.BLOG_POST_PATH)
@RequiredArgsConstructor
public class BlogPostController {
	
	@Autowired
	private BlogPostService service;

    @GetMapping
    public List<BlogPostResponse> getAll() {
        return service.getAllPosts();
    }

    @GetMapping("/{id}")
    public BlogPostResponse getById(@PathVariable Long id) {
        return service.getPostById(id);
    }

    @PostMapping
    public BlogPostResponse create(@RequestBody BlogPostRequest request) {
        return service.createPost(request);
    }
}
