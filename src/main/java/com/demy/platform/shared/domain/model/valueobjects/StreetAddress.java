package com.demy.platform.shared.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record StreetAddress(
        @Column(nullable = false, length = 100)
        @NotBlank
        String street,

        @Column(nullable = false, length = 50)
        @NotBlank
        String district,

        @Column(nullable = false, length = 50)
        @NotBlank
        String province,

        @Column(nullable = false, length = 50)
        @NotBlank
        String department
) {
    public StreetAddress() {
        this("", "", "", "");
    }

    public StreetAddress {
        if (street == null || street.isBlank())
            throw new IllegalArgumentException("Street cannot be null or empty");
        if (street.length() > 100)
            throw new IllegalArgumentException("Street cannot exceed 100 characters");
        if (district == null || district.isBlank())
            throw new IllegalArgumentException("District cannot be null or empty");
        if (district.length() > 50)
            throw new IllegalArgumentException("District cannot exceed 50 characters");
        if (province == null || province.isBlank())
            throw new IllegalArgumentException("Province cannot be null or empty");
        if (province.length() > 50)
            throw new IllegalArgumentException("Province cannot exceed 50 characters");
        if (department == null || department.isBlank())
            throw new IllegalArgumentException("Department cannot be null or empty");
        if (department.length() > 50)
            throw new IllegalArgumentException("Department cannot exceed 50 characters");
    }
}
