package com.project.portfolio.service.auth;

import com.project.portfolio.model.request.AuthenticationRequest;
import com.project.portfolio.model.request.RegisterRequest;
import com.project.portfolio.model.response.AuthenticationResponse;

public interface AuthService {
	AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse login(AuthenticationRequest request);
}
