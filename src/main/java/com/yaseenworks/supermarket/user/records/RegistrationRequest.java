package com.yaseenworks.supermarket.user.records;

import com.yaseenworks.supermarket.user.models.Name;
import jakarta.validation.constraints.*;

public record RegistrationRequest(
        @NotNull(message = "Name is required")
        Name name,
        @Email(message = "Invalid Email Address")
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Password is required")
        @Size(min = 7, message = "Password should be longer than 6 characters")
        String password,
        @Pattern(regexp = "^\\+?[1-9][0-9]{7,14}$", message = "Invalid Phone Number")
        @NotBlank(message = "Phone Number is required")
        String phoneNumber) {

}
