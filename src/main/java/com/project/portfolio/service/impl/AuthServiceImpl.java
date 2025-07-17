package com.project.portfolio.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.portfolio.entity.UserEntity;
import com.project.portfolio.model.request.AuthenticationRequest;
import com.project.portfolio.model.request.RegisterRequest;
import com.project.portfolio.model.response.AuthenticationResponse;
import com.project.portfolio.repository.UserRepository;
import com.project.portfolio.security.JwtUtil;
import com.project.portfolio.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final JwtUtil jwtUtil;

	@Override
	public AuthenticationResponse register(RegisterRequest request) {
		if (userRepository.existsByEmail(request.email())) {
			throw new RuntimeException("Email already registered");
		}

		UserEntity user = new UserEntity();
		user.setEmail(request.email());
		user.setPassword(passwordEncoder.encode(request.password()));
		userRepository.save(user);

		/*
		 * After the user is stored in db, we fetch it from the db to ensure the user is
		 * not missing any details. This might result in a little performance issue with
		 * the extra DB Call. But this can be moderated by creating a custom user
		 * details which takes an user entity and returns the details.
		 */
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

//		Not a good pratice as it can expose raw passowrds and user details
//		UserDetails userDetails = User.withUsername(user.getEmail()).password(user.getPassword())
//				.authorities(Collections.emptyList()).build();

		String jwtToken = jwtUtil.generateToken(userDetails);
		return new AuthenticationResponse(jwtToken);
	}

	@Override
	public AuthenticationResponse login(AuthenticationRequest request) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

//		Not a good practice as it can contain raw password
//		UserDetails userDetails = User.withUsername(request.getEmail()).password(request.getPassword())
//				.authorities(Collections.emptyList()).build();

//		Fetching from the Authentication Manager instead
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

//		User details can be fetched in below way as well, but fetching from authentication manager is best as it is the authenticated user
//		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

		// Fetch your actual UserEntity from DB (safe at this point since auth
		// succeeded)
//		UserEntity user = userRepository.findByEmail(userDetails.getUsername())
//				.orElseThrow(() -> new RuntimeException("User not found in DB"));

		String jwtToken = jwtUtil.generateToken(userDetails);
		return new AuthenticationResponse(jwtToken);
	}
	
//	To do
//	Throw a custom exception or return proper HTTP status:
//	Log failed authentication attempts and token generation for debugging and auditing.
//	In production apps:
//	Access tokens expire quickly (5–15 min)
//	Use a long-lived refresh token to get new access tokens
}
