package com.project.portfolio.service;

import com.project.portfolio.model.request.ProfileRequest;
import com.project.portfolio.model.response.ProfileResponse;

public interface ProfileService {
	
	ProfileResponse getProfile();
    ProfileResponse updateProfile(ProfileRequest request);
}
