package com.project.portfolio.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {

	private Long id;
    private String name;
    private String description;
    private String techStack;
    private String githubLink;
    private String demoLink;
}
