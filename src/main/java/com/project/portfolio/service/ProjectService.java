package com.project.portfolio.service;

import java.util.List;

import com.project.portfolio.model.request.ProjectRequest;
import com.project.portfolio.model.response.ProjectResponse;

public interface ProjectService {
	
	List<ProjectResponse> getAllProjects();
    ProjectResponse getProjectById(Long id);
    ProjectResponse createProject(ProjectRequest request);
}
