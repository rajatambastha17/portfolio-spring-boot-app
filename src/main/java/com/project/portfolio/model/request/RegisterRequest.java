package com.project.portfolio.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class RegisterRequest {
//	
//	@Email
//	@NotBlank
//	private String email;
//
//	@NotBlank
//	private String password;
//}

public record RegisterRequest(

		@Email @NotBlank String email,

		@NotBlank String password) {
}
