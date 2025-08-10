package com.demy.platform.shared.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record PhoneNumber(
        @Column(nullable = false, length = 5)
        String countryCode,

        @Column(nullable = false, length = 15)
        String number
) {
    public PhoneNumber() {
        this("", "");
    }
}
