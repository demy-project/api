package com.demy.platform.shared.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record DniNumber(
        @Column(nullable = false, unique = true, length = 8)
        String dniNumber
) {
    public DniNumber() {
        this("");
    }

    public DniNumber {
        if (dniNumber == null || dniNumber.isBlank())
            throw new IllegalArgumentException("DNI number cannot be null or empty");
        if (dniNumber.length() != 8)
            throw new IllegalArgumentException("DNI number must be exactly 8 characters long");
        if (!dniNumber.matches("\\d+"))
            throw new IllegalArgumentException("DNI number must contain only digits");
    }
}
