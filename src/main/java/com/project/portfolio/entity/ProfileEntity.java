package com.project.portfolio.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileEntity {
    @Id
    private Long id = 1L;

    @Column(name = "name")
    private String name;
    
    @Column(name = "bio")
    private String bio;
    
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
    @Column(name = "location")
    private String location;
}
