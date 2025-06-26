package com.project.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.portfolio.entity.RefreshTokenEntity;
import com.project.portfolio.entity.UserEntity;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

	Optional<RefreshTokenEntity> findByToken(String token);

	int deleteByUser(UserEntity user);
}
