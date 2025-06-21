package com.project.portfolio.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.project.portfolio.entity.ProfileEntity;
import com.project.portfolio.model.request.ProfileRequest;
import com.project.portfolio.model.response.ProfileResponse;
import com.project.portfolio.repository.ProfileRepository;
import com.project.portfolio.service.ProfileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

	private final ProfileRepository profileRepository;

    private String getCurrentUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public ProfileResponse getProfile() {
        String email = getCurrentUserEmail();
        ProfileEntity profile = profileRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return new ProfileResponse(
                profile.getId(),
                profile.getName(),
                profile.getBio(),
                profile.getEmail(),
                profile.getLocation()
        );
    }

    @Override
    public ProfileResponse updateProfile(ProfileRequest request) {
        String email = getCurrentUserEmail();

        ProfileEntity profile = profileRepository.findByEmail(email).orElse(new ProfileEntity());
        profile.setEmail(email);
        profile.setName(request.getName());
        profile.setBio(request.getBio());
        profile.setLocation(request.getLocation());

        ProfileEntity saved = profileRepository.save(profile);

        return new ProfileResponse(
                saved.getId(),
                saved.getName(),
                saved.getBio(),
                saved.getEmail(),
                saved.getLocation()
        );
    }

}
