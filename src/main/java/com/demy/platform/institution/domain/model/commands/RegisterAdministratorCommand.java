package com.demy.platform.institution.domain.model.commands;

import com.demy.platform.shared.domain.model.valueobjects.*;

import java.util.Set;

public record RegisterAdministratorCommand(
        FullName fullName,
        EmailAddress emailAddress,
        PhoneNumber phoneNumber,
        DniNumber dniNumber,
        Set<AcademyId> academyIds
) {
    public RegisterAdministratorCommand {
        if (fullName == null)
            throw new IllegalArgumentException("Full name cannot be null");
        if (emailAddress == null)
            throw new IllegalArgumentException("Email address cannot be null");
        if (phoneNumber == null)
            throw new IllegalArgumentException("Phone number cannot be null");
        if (dniNumber == null)
            throw new IllegalArgumentException("Dni number cannot be null");
        if (academyIds == null)
            throw new IllegalArgumentException("Academy IDs cannot be null");
    }
}
