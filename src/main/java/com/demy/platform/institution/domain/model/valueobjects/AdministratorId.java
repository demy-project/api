package com.demy.platform.institution.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record AdministratorId(
        @NotBlank
        @Column(nullable = false, unique = true)
        String administratorId
) {
    public AdministratorId {
        if (administratorId == null || administratorId.isBlank())
            throw new IllegalArgumentException("Administrator ID cannot be null or blank");
    }
}
