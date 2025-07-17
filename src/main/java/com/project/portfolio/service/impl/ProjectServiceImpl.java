package com.project.portfolio.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.project.portfolio.entity.ProjectEntity;
import com.project.portfolio.model.request.ProjectRequest;
import com.project.portfolio.model.response.ProjectResponse;
import com.project.portfolio.repository.ProjectRepository;
import com.project.portfolio.service.ProjectService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository repo;

	@Override
	public List<ProjectResponse> getAllProjects() {
		return repo.findAll().stream().map(p -> new ProjectResponse(p.getId(), p.getProjectName(), p.getDescription(),
				p.getTechStack(), p.getGithubLink(), p.getDemoLink())).collect(Collectors.toList());
	}

	@Override
	public ProjectResponse getProjectById(Long id) {
		ProjectEntity p = repo.findById(id).orElseThrow();
		return new ProjectResponse(p.getId(), p.getProjectName(), p.getDescription(), p.getTechStack(),
				p.getGithubLink(), p.getDemoLink());
	}

	@Override
	public ProjectResponse createProject(ProjectRequest request) {
		ProjectEntity p = new ProjectEntity(null, request.getName(), request.getDescription(), request.getTechStack(),
				request.getGithubLink(), request.getDemoLink());
		
		ProjectEntity saved = repo.save(p);
		return new ProjectResponse(saved.getId(), saved.getProjectName(), saved.getDescription(), saved.getTechStack(),
				saved.getGithubLink(), saved.getDemoLink());
	}
}
