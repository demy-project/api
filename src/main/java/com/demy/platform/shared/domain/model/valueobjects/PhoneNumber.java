package com.demy.platform.shared.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record PhoneNumber(
        String countryCode,
        String number
) {
}
