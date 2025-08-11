package com.demy.platform.institution.interfaces.rest.resources;

public record AdministratorResource(
        Long id,
        String firstName,
        String lastName,
        String emailAddress,
        String phoneNumber,
        String dniNumber,
        String academyId
) {
}
