package com.demy.platform.institution.interfaces.rest.resources;

    public record RegisterAdministratorResource(
        String firstName,
        String lastName,
        String countryCode,
        String phone,
        String dniNumber
) {
    public RegisterAdministratorResource {
        if (firstName == null || firstName.isBlank())
            throw new IllegalArgumentException("First name is required");
        if (lastName == null || lastName.isBlank())
            throw new IllegalArgumentException("Email address is required");
        if (phone == null || phone.isBlank())
            throw new IllegalArgumentException("Phone number is required");
        if (dniNumber == null || dniNumber.isBlank())
            throw new IllegalArgumentException("DNI number is required");
    }
}
