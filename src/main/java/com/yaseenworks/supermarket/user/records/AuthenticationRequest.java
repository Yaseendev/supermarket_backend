package com.yaseenworks.supermarket.user.records;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthenticationRequest (
        @Email(message = "Invalid Email Address")
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Password is required")
     String password) {
}
