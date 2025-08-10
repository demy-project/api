package com.demy.platform.shared.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public record AcademyId(
        @Column(nullable = false, unique = true)
        @NotNull
        Long academyId
) {
    public AcademyId() {
        this(0L);
    }
}
