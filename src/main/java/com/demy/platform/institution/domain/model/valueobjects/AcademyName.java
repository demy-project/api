package com.demy.platform.institution.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record AcademyName(
        @Column(nullable = false, length = 80)
        @NotBlank
        String name
) {
}
