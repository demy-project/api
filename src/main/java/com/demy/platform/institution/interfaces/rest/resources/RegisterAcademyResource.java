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
    public RegisterAcademyResource {
        if (administratorId == null || administratorId <= 0)
            throw new IllegalArgumentException("Administrator ID is required");
        if (academyName == null || academyName.isBlank())
            throw new IllegalArgumentException("Academy name is required");
        if (academyDescription == null || academyDescription.isBlank())
            throw new IllegalArgumentException("Academy description is required");
        if (street == null || street.isBlank())
            throw new IllegalArgumentException("Street address is required");
        if (district == null || district.isBlank())
            throw new IllegalArgumentException("District is required");
        if (province == null || province.isBlank())
            throw new IllegalArgumentException("Province is required");
        if (department == null || department.isBlank())
            throw new IllegalArgumentException("Department is required");
        if (emailAddress == null || emailAddress.isBlank())
            throw new IllegalArgumentException("Email address is required");
        if (phoneNumber == null || phoneNumber.isBlank())
            throw new IllegalArgumentException("Phone number is required");
        if (ruc == null || ruc.isBlank())
            throw new IllegalArgumentException("RUC is required");
    }
}
