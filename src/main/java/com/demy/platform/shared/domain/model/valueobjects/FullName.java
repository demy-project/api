package com.demy.platform.shared.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record FullName(
        @NotBlank
        @Column(nullable = false, length = 50)
        String firstName,

        @NotBlank
        @Column(nullable = false, length = 50)
        String lastName
) {
}
