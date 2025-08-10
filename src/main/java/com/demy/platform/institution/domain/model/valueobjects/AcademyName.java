package com.demy.platform.institution.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record AcademyName(
        @Column(nullable = false, length = 80)
        @NotBlank
        String academyName
) {
    public AcademyName() {
        this("");
    }

    public AcademyName {
        if (academyName == null || academyName.isBlank())
            throw new IllegalArgumentException("Academy name cannot be null or empty");
        if (academyName.length() > 80)
            throw new IllegalArgumentException("Academy name cannot exceed 80 characters");
    }
}
