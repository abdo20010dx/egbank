package com.banking.egbank.shared.exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        // Create a map to hold field-specific error messages
        Map<String, String> errors = new HashMap<>();

        // Iterate over all validation errors
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        // Create an instance of ValidationErrorDetails with detailed error messages
        ValidationErrorDetails errorDetails = new ValidationErrorDetails(new Date(), "Validation failed", errors, request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "An unexpected error occurred", request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static class ValidationErrorDetails {

        private Date timestamp;
        private String message;
        private Map<String, String> errors;
        private String details;

        public ValidationErrorDetails(Date timestamp, String message, Map<String, String> errors, String details) {
            this.timestamp = timestamp;
            this.message = message;
            this.errors = errors;
            this.details = details;
        }

        // Getters and setters
        public Date getTimestamp() {
            return timestamp;
        }

        public String getMessage() {
            return message;
        }

        public Map<String, String> getErrors() {
            return errors;
        }

        public String getDetails() {
            return details;
        }
    }

    public static class ErrorDetails {

        private Date timestamp;
        private String message;
        private String details;

        public ErrorDetails(Date timestamp, String message, String details) {
            this.timestamp = timestamp;
            this.message = message;
            this.details = details;
        }

        // Getters and setters
        public Date getTimestamp() {
            return timestamp;
        }

        public String getMessage() {
            return message;
        }

        public String getDetails() {
            return details;
        }
    }
}
