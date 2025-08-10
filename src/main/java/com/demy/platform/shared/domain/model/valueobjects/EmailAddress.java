package com.demy.platform.shared.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record EmailAddress(
        @Column(nullable = false, unique = true)
        String email
) {
    public EmailAddress() {
        this("");
    }

    public EmailAddress {
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email address cannot be null or empty");
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))
            throw new IllegalArgumentException("Invalid email address format");
    }
}
