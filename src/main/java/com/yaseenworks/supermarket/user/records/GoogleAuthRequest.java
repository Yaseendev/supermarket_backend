package com.yaseenworks.supermarket.user.records;

import com.yaseenworks.supermarket.user.models.Name;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GoogleAuthRequest(

        @NotBlank(message = "Google access token is required")
        String accessToken,
        @NotNull(message = "Name is required")
        Name name
) {
}
