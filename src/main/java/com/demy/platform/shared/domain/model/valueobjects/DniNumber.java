package com.demy.platform.shared.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record DniNumber(
        @Column(nullable = false, unique = true)
        String dniNumber
) {
}
