package com.project.portfolio.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    
	private Long id;
    private String name;
    private String bio;
    private String email;
    private String location;
}
