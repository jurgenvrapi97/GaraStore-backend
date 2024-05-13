package com.jurgenvrapi.garastore.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "Email is required")
        @Email
        String email,

        @NotBlank(message = "Password is required")
        String password
) {
}