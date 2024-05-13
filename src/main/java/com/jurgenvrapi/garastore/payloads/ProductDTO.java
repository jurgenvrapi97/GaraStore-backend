package com.jurgenvrapi.garastore.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

public record ProductDTO(
        @NotBlank(message = "Product name is required")
        @Size(min = 3, message = "Product name must be at least 3 characters long")
        String productName,

        @NotNull(message = "Price is required")
        Double price,

        @NotBlank(message = "Description is required")
        String description
) {
}
