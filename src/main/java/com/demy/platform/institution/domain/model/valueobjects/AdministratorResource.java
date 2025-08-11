package com.demy.platform.institution.domain.model.valueobjects;

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
