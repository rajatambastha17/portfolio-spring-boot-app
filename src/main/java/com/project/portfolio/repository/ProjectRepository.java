package com.project.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.portfolio.entity.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {

}
