package com.banking.egbank.modules.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.banking.egbank.jwt.JwtUtil;
import com.banking.egbank.modules.user.dto.AuthenticationRequest;
import com.banking.egbank.modules.user.dto.SignupRequest;
import com.banking.egbank.modules.user.entities.UserEntity;
import com.banking.egbank.modules.user.repository.UserRepository;
import com.banking.egbank.shared.common.apiResponse.ResStructure;
import com.banking.egbank.shared.common.translations.KeysMessages;
import com.banking.egbank.shared.common.translations.Langs;
import com.banking.egbank.shared.exceptions.GlobalExceptionHandler.ValidationErrorDetails;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User registered successfully",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid input",
                content = @Content),
        @ApiResponse(responseCode = "409", description = "Email already exists",
                content = @Content)
    })
    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody @Valid SignupRequest signupRequest) {

        // Check if the email is already in use
        UserEntity userEntity = userRepository.findByEmail(signupRequest.getEmail());
        if (userEntity != null) {
            return ResStructure.errorResponse(Langs.EN, KeysMessages.ACCOUNT_EXIST, HttpStatus.CONFLICT);
        }

        // Create a new user entity
        UserEntity newUser = new UserEntity();
        newUser.setFull_name(signupRequest.getFullName());
        newUser.setEmail(signupRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        // Save the user
        userRepository.save(newUser);

        return ResStructure.successResponse(Langs.EN, "", KeysMessages.REGISTER, HttpStatus.OK);
    }

    @Operation(summary = "Authenticate user and generate JWT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "JWT generated successfully",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class))}),
        @ApiResponse(responseCode = "401", description = "Invalid credentials",
                content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error",
                content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        // Authenticate user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // Load user details
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        System.out.println("User Details: ++++++__________" + userDetails);

        // Create claims map
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        System.out.println("Claims: ++++++__________" + claims);
        // Generate token with claims
        String token = jwtUtil.createToken(claims, userDetails.getUsername());

        // Return the token
        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return ResStructure.successResponse(Langs.EN, response, KeysMessages.LOGIN, HttpStatus.OK);
    }

    @Operation(summary = "Get the details of the currently authenticated user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User details fetched successfully",
                content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserEntity.class))}),
        @ApiResponse(responseCode = "401", description = "Unauthorized",
                content = @Content)
    })
    @GetMapping("/whoami")
    public ResponseEntity<Object> whoami(HttpServletRequest request) {
        System.out.println("""
                `token:`__________________________________`
                """);
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.extractUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // Fetch the user entity
        UserEntity user = userRepository.findByEmail(userDetails.getUsername());

        return ResStructure.successResponse(Langs.EN, user, KeysMessages.LOGIN, HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ValidationErrorDetails errorDetails = new ValidationErrorDetails(new Date(), "Validation failed", errors, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
