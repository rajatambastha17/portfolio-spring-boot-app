package com.project.portfolio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.portfolio.model.request.ProjectRequest;
import com.project.portfolio.model.response.ProjectResponse;
import com.project.portfolio.service.ProjectService;
import com.project.portfolio.util.Constants;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.PROJECT_PATH)
@RequiredArgsConstructor
public class ProjectController {
	
	@Autowired
	private ProjectService service;

    @GetMapping
    public List<ProjectResponse> getAll() {
        return service.getAllProjects();
    }

    @GetMapping("/{id}")
    public ProjectResponse getById(@PathVariable Long id) {
        return service.getProjectById(id);
    }

    @PostMapping
    public ProjectResponse create(@RequestBody ProjectRequest request) {
        return service.createProject(request);
    }
}
