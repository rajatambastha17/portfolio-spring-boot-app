package com.project.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.portfolio.model.request.ProfileRequest;
import com.project.portfolio.model.response.ProfileResponse;
import com.project.portfolio.service.ProfileService;
import com.project.portfolio.util.Constants;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Constants.PROFILE_PATH)
@RequiredArgsConstructor
public class ProfileController {

	@Autowired
	private ProfileService service;

	@GetMapping
	public ProfileResponse getProfile() {
		return service.getProfile();
	}

	@PostMapping
	public ProfileResponse updateProfile(@RequestBody ProfileRequest request) {
		return service.updateProfile(request);
	}
}
