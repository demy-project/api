package com.demy.platform.institution.domain.exceptions;

import com.demy.platform.shared.domain.exceptions.DomainException;
import com.demy.platform.shared.domain.model.valueobjects.DniNumber;

public class AdministratorAlreadyExistsException extends DomainException {
    public AdministratorAlreadyExistsException(DniNumber dniNumber) {
        super("administrator.already.exists", dniNumber.dniNumber());
    }
}
