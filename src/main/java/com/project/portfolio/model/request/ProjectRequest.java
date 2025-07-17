package com.project.portfolio.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequest {
    
	private String name;
    private String description;
    private String techStack;
    private String githubLink;
    private String demoLink;
}
