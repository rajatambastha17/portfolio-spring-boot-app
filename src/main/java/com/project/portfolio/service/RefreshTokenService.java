package com.project.portfolio.service;

import com.project.portfolio.entity.RefreshTokenEntity;
import com.project.portfolio.entity.UserEntity;

public interface RefreshTokenService {
	
	RefreshTokenEntity createRefreshTokenEntity(UserEntity user);
	
    boolean isValid(String token);
    
    RefreshTokenEntity verifyExpiration(RefreshTokenEntity token);
    
    void revokeRefreshTokenEntity(RefreshTokenEntity token);
    
    int deleteByUser(UserEntity user);
    
    RefreshTokenEntity getTokenOrThrow(String token);
}
