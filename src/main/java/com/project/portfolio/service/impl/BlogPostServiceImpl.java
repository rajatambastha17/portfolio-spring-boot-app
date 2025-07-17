package com.project.portfolio.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.portfolio.entity.BlogPostEntity;
import com.project.portfolio.model.request.BlogPostRequest;
import com.project.portfolio.model.response.BlogPostResponse;
import com.project.portfolio.repository.BlogPostRepository;
import com.project.portfolio.service.BlogPostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BlogPostServiceImpl implements BlogPostService {

	private final BlogPostRepository repo;

    @Override
    public List<BlogPostResponse> getAllPosts() {
        return repo.findAll().stream()
            .map(p -> new BlogPostResponse(p.getId(), p.getTitle(), p.getContent(), p.getCreatedAt()))
            .collect(Collectors.toList());
    }

    @Override
    public BlogPostResponse getPostById(Long id) {
        BlogPostEntity post = repo.findById(id).orElseThrow();
        return new BlogPostResponse(post.getId(), post.getTitle(), post.getContent(), post.getCreatedAt());
    }

    @Override
    public BlogPostResponse createPost(BlogPostRequest request) {
        BlogPostEntity post = new BlogPostEntity();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCreatedAt(LocalDateTime.now());
        
        BlogPostEntity saved = repo.save(post);
        return new BlogPostResponse(saved.getId(), saved.getTitle(), saved.getContent(), saved.getCreatedAt());
    }

}
