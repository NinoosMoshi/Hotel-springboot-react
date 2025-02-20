package com.ninos.entities;

import com.ninos.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
    private String firstName;
    private String lastName;

    @NotBlank(message = "Phone Number is required")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private UserRole role;   // e.g CUSTOMER, ADMIN

    private Boolean isActive;
    private final LocalDateTime creationAt = LocalDateTime.now();



}
