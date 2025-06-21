package com.project.portfolio.service.impl;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.portfolio.entity.UserEntity;
import com.project.portfolio.model.request.AuthenticationRequest;
import com.project.portfolio.model.request.RegisterRequest;
import com.project.portfolio.model.response.AuthenticationResponse;
import com.project.portfolio.repository.UserRepository;
import com.project.portfolio.security.JwtUtil;
import com.project.portfolio.service.auth.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        var userDetails = User.withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(Collections.emptyList())
                .build();

        String jwtToken = jwtUtil.generateToken(userDetails);
        return new AuthenticationResponse(jwtToken);
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var userDetails = User.withUsername(request.getEmail())
                .password(request.getPassword())
                .authorities(Collections.emptyList())
                .build();

        String jwtToken = jwtUtil.generateToken(userDetails);
        return new AuthenticationResponse(jwtToken);
    }
}
