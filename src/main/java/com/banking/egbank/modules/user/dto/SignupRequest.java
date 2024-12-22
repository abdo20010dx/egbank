package com.banking.egbank.modules.user.dto;

import java.util.Set;

import com.banking.egbank.modules.role.entities.RoleEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Request object for user registration")
public class SignupRequest {

    @Schema(description = "The full name of the user", example = "John Doe")
    @NotNull(message = "Full name is mandatory")
    @Size(min = 2, max = 50, message = "Full name must be between 2 and 50 characters")
    private String fullName;

    @Schema(description = "The email of the user", example = "user@example.com")
    @NotNull(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(description = "The password of the user", example = "password123")
    @NotNull(message = "Password is mandatory")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;

    @Schema(description = "The roles of the user", example = "[{\"id\": 1, \"name\": \"ADMIN\"}]")
    private Set<RoleEntity> roles;

    public SignupRequest(String fullName, String email, String password, Set<RoleEntity> roles) {
        this.roles = roles;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
