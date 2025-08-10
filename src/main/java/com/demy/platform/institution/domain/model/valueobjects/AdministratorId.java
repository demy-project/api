package com.demy.platform.institution.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

/**
 * AdministratorId Value Object
 * Represents the unique identifier for an administrator in the institution domain.
 * This class encapsulates the administrator's ID, ensuring it is not null and greater than zero.
 *
 * @summary
 * Value object representing the unique identifier for an administrator in the institution domain.
 * This class is used to encapsulate the administrator's ID, ensuring it is not null and greater than zero.
 *
 * @see IllegalArgumentException
 * @since 1.0.0
 */
@Embeddable
public record AdministratorId(
        @NotNull
        @Column(nullable = false, unique = true)
        Long administratorId
) {
    /**
     * Default constructor for JPA
     */
    public AdministratorId() {
        this(0L);
    }

    /**
     * Constructor for AdministratorId
     *
     * @param administratorId the unique identifier for the administrator
     * @throws IllegalArgumentException if administratorId is null or less than or equal to zero
     */
    public AdministratorId {
        if (administratorId == null || administratorId <= 0)
            throw new IllegalArgumentException("Administrator ID cannot be null or less than or equal to zero");
    }
}
