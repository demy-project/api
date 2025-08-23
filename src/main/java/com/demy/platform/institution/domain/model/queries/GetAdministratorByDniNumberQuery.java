package com.demy.platform.institution.domain.model.queries;

import com.demy.platform.shared.domain.model.valueobjects.DniNumber;

public record GetAdministratorByDniNumberQuery(DniNumber dniNumber) {

    public GetAdministratorByDniNumberQuery {
        if (dniNumber == null)
            throw new IllegalArgumentException("DNI number is required");
    }
}
