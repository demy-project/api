package com.demy.platform.shared.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record FullName(
        @NotBlank
        @Column(nullable = false, length = 50)
        String firstName,

        @NotBlank
        @Column(nullable = false, length = 50)
        String lastName
) {
    public FullName() {
        this("", "");
    }

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
