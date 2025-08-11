package com.demy.platform.institution.interfaces.rest.resources;

public record RegisterAcademyResource(
        Long administratorId,
        String academyName,
        String academyDescription,
        String street,
        String district,
        String province,
        String department,
        String emailAddress,
        String phoneNumber,
        String ruc
) {
}
