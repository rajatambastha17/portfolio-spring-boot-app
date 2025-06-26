package com.project.portfolio.service.impl;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.portfolio.entity.RefreshTokenEntity;
import com.project.portfolio.entity.UserEntity;
import com.project.portfolio.repository.RefreshTokenRepository;
import com.project.portfolio.service.RefreshTokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

	private final RefreshTokenRepository refreshTokenRepository;

	@Value("${jwt.refreshTokenExpirationMs}")
	private Long refreshTokenDurationMs;

	@Override
	public RefreshTokenEntity createRefreshTokenEntity(UserEntity user) {

		RefreshTokenEntity refreshToken = new RefreshTokenEntity();
		refreshToken.setUser(user);
		refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
		refreshToken.setToken(UUID.randomUUID().toString());
		refreshToken.setRevoked(false);
		return refreshTokenRepository.save(refreshToken);
	}

	@Override
	public boolean isValid(String token) {

		return refreshTokenRepository.findByToken(token)
				.map(rt -> !rt.isRevoked() && rt.getExpiryDate().isAfter(Instant.now())).orElse(false);
	}

	@Override
	public RefreshTokenEntity verifyExpiration(RefreshTokenEntity token) {

		if (token.getExpiryDate().isBefore(Instant.now())) {
			token.setRevoked(true);
			refreshTokenRepository.save(token);
			throw new RuntimeException("Refresh token expired. Please login again.");
		}
		return token;
	}

	@Override
	public void revokeRefreshTokenEntity(RefreshTokenEntity token) {

		token.setRevoked(true);
		refreshTokenRepository.save(token);
	}

	@Override
	public int deleteByUser(UserEntity user) {
		return refreshTokenRepository.deleteByUser(user);
	}

	@Override
	public RefreshTokenEntity getTokenOrThrow(String token) {
		return refreshTokenRepository.findByToken(token)
				.orElseThrow(() -> new RuntimeException("Invalid refresh token"));
	}

}
