package com.demy.platform.institution.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public record AdministratorId(
        @NotNull
        @Column(nullable = false, unique = true)
        Long administratorId
) {
    public AdministratorId {
        if (administratorId == null || administratorId <= 0)
            throw new IllegalArgumentException("Administrator ID cannot be null or less than or equal to zero");
    }
}
