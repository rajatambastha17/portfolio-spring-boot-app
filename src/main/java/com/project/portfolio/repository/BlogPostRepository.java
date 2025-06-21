package com.project.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.portfolio.entity.BlogPostEntity;

public interface BlogPostRepository extends JpaRepository<BlogPostEntity, Long> {

}
