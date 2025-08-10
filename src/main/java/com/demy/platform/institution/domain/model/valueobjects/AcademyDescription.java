package com.demy.platform.institution.domain.model.valueobjects;

import jakarta.persistence.Column;

public record AcademyDescription(
        @Column(nullable = false)
        String description
) {
}
