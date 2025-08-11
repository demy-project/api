package com.demy.platform.institution.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

/**
 * Value object representing the unique identifier of an administrator.
 * <p>
 * Encapsulates the administrator ID ensuring it is not null and greater than zero.
 * Used as an embeddable in the persistence layer.
 *
 * @author Salim Ramirez
 * @see IllegalArgumentException
 * @since 1.0.0
 */
@Embeddable
public record AdministratorId(
        @Column(nullable = false, unique = true)
        @NotNull
        Long administratorId
) {
    /**
     * Default constructor required by JPA.
     * Initializes the administrator ID with a default value of 0.
     */
    public AdministratorId() {
        this(0L);
    }

    /**
     * Constructs an AdministratorId with the specified ID.
     *
     * @param administratorId the unique identifier of the administrator
     * @throws IllegalArgumentException if administratorId is null or less than or equal to zero
     */
    public AdministratorId {
        if (administratorId == null || administratorId <= 0)
            throw new IllegalArgumentException("Administrator ID cannot be null or less than or equal to zero");
    }
}
