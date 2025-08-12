package com.demy.platform.institution.domain.model.commands;

import com.demy.platform.shared.domain.model.valueobjects.*;

public record RegisterAdministratorCommand(
        PersonName personName,
        PhoneNumber phoneNumber,
        DniNumber dniNumber
) {
    public RegisterAdministratorCommand {
        if (personName == null)
            throw new IllegalArgumentException("Person name cannot be null");
        if (phoneNumber == null)
            throw new IllegalArgumentException("Phone number cannot be null");
        if (dniNumber == null)
            throw new IllegalArgumentException("DNI number cannot be null");
    }
}
