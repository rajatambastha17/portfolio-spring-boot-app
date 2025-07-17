package com.project.portfolio.model.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPostResponse {

	private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
}
