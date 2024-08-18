package com.banking.egbank.modules.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Request object for user authentication")
public class AuthenticationRequest {

    @Schema(description = "The email of the user", example = "user@example.com")
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(description = "The password of the user", example = "password123")
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;

    // Constructors
    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
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
