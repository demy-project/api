package com.demy.platform.institution.domain.exceptions;

import com.demy.platform.institution.domain.errors.InstitutionError;
import com.demy.platform.shared.domain.exceptions.DomainException;
import com.demy.platform.shared.domain.model.valueobjects.DniNumber;

public class AdministratorAlreadyExistsException extends DomainException {
    public AdministratorAlreadyExistsException(DniNumber dniNumber) {
        super(InstitutionError.ADMINISTRATOR_ALREADY_EXISTS, dniNumber.dniNumber());
    }
}
