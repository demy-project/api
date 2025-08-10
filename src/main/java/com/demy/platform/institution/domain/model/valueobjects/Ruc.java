package com.demy.platform.institution.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

/**
 * RUC (Registro Único de Contribuyentes) Value Object
 * Represents a unique taxpayer registration number in Peru.
 * Enforces validation rules for non-blank, length, and digit-only constraints.
 *
 * @summary
 * Represents a RUC (Registro Único de Contribuyentes) value object with validation.
 *
 * @see IllegalArgumentException
 * @since 1.0.0
 */
@Embeddable
public record Ruc(
        @NotBlank
        @Column(nullable = false, length = 11, unique = true)
        String ruc
) {
    /**
     * Default constructor for JPA
     */
    public Ruc() {
        this("");
    }

    /**
     * Constructor with validation
     * @param ruc RUC string
     * @throws IllegalArgumentException if the RUC is invalid
     */
    public Ruc {
        if (ruc == null || ruc.isBlank())
            throw new IllegalArgumentException("RUC cannot be null or empty");
        if (ruc.length() != 11)
            throw new IllegalArgumentException("RUC must be 11 characters long");
        if (!ruc.matches("\\d{11}"))
            throw new IllegalArgumentException("RUC must contain only digits");
    }
}
