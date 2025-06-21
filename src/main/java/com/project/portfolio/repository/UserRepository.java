package com.project.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.portfolio.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
	Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}