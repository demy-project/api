package com.demy.platform.shared.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record StreetAddress(
        @Column(nullable = false, length = 100)
        @NotBlank
        String street,

        @Column(nullable = false, length = 50)
        @NotBlank
        String district,

        @Column(nullable = false, length = 50)
        @NotBlank
        String province,

        @Column(nullable = false, length = 50)
        @NotBlank
        String department
) {
}
