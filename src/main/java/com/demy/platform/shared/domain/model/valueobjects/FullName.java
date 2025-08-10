package com.demy.platform.shared.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

/**
 * Full Name Value Object
 * Represents a person's full name with first and last names.
 * Enforces validation rules for non-blank, length, and space constraints.
 *
 * @summary
 * This value object is used to encapsulate a person's full name in a structured way,
 * ensuring that the first and last names are valid according to specified rules.
 *
 * @see IllegalArgumentException
 * @since 1.0.0
 */
@Embeddable
public record FullName(
        @NotBlank
        @Column(nullable = false, length = 50)
        String firstName,

        @NotBlank
        @Column(nullable = false, length = 50)
        String lastName
) {
    /**
     * Default constructor for JPA
     */
    public FullName() {
        this("", "");
    }

    /**
     * Constructor with validation
     *
     * @param firstName First name
     * @param lastName Last name
     * @throws IllegalArgumentException if first name or last name is null, blank, exceeds length, or contains spaces
     */
    public FullName {
        if (firstName == null || firstName.isBlank())
            throw new IllegalArgumentException("First name cannot be null or blank");
        if (firstName.length() > 50)
            throw new IllegalArgumentException("First name cannot exceed 50 characters");
        if (firstName.contains(" "))
            throw new IllegalArgumentException("First name cannot contain spaces");
        if (lastName == null || lastName.isBlank())
            throw new IllegalArgumentException("Last name cannot be null or blank");
        if (lastName.length() > 50)
            throw new IllegalArgumentException("Last name cannot exceed 50 characters");
        if (lastName.contains(" "))
            throw new IllegalArgumentException("Last name cannot contain spaces");
    }
}
