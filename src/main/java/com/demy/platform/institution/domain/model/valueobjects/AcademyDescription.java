package com.demy.platform.institution.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record AcademyDescription(
        @Column(nullable = false)
        @NotBlank
        String description
) {
    /**
     * Default constructor required by JPA.
     * Initializes the description with a default empty string.
     */
    public AcademyDescription() {
        this("");
    }
}
