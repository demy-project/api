package com.demy.platform.shared.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record EmailAddress(
        @Column(nullable = false, unique = true)
        String email
) {
}
