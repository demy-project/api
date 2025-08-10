package com.demy.platform.shared.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

/**
 * AcademyId Value Object
 * Represents the unique identifier for an academy in the shared domain.
 * This class encapsulates the academy's ID, ensuring it is not null and greater than zero.
 *
 * @summary
 * Value object representing the unique identifier for an academy in the shared domain.
 * This class is used to encapsulate the academy's ID, ensuring it is not null and greater than zero.
 *
 * @see IllegalArgumentException
 * @since 1.0.0
 */
@Embeddable
public record AcademyId(
        @Column(nullable = false, unique = true)
        @NotNull
        Long academyId
) {
    /**
     * Default constructor for JPA
     */
    public AcademyId() {
        this(0L);
    }

    /**
     * Constructor for AcademyId
     *
     * @param academyId the unique identifier for the academy
     * @throws IllegalArgumentException if academyId is null or less than or equal to zero
     */
    public AcademyId {
        if (academyId == null || academyId <= 0)
            throw new IllegalArgumentException("Academy ID cannot be null or less than or equal to zero");
    }
}
