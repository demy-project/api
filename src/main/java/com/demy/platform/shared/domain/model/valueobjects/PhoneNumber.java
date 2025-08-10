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

    public PhoneNumber {
        if (countryCode == null || countryCode.isBlank())
            throw new IllegalArgumentException("Country code cannot be null or empty");
        if (countryCode.length() > 5)
            throw new IllegalArgumentException("Country code cannot be longer than 5 characters");
        if (!countryCode.matches("^\\+?\\d{1,5}$"))
            throw new IllegalArgumentException("Invalid country code");
        if (number == null || number.isBlank())
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        if (number.length() > 15)
            throw new IllegalArgumentException("Phone number cannot be longer than 15 characters");
        if (!number.replaceAll("[\\s\\-]", "").matches("^\\d{6,12}$"))
            throw new IllegalArgumentException("Invalid local number");
    }

    public String getFullNumber() {
        return countryCode.replace("+", "") + number.replaceAll("[\\s\\-]", "");
    }

    public String getFormatted() {
        return String.format("%s %s", countryCode, number);
    }
}
