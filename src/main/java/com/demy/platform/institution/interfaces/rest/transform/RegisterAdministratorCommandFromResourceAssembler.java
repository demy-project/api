package com.demy.platform.institution.interfaces.rest.transform;

import com.demy.platform.institution.domain.model.commands.RegisterAdministratorCommand;
import com.demy.platform.institution.interfaces.rest.resources.RegisterAdministratorResource;
import com.demy.platform.shared.domain.model.valueobjects.*;

public class RegisterAdministratorCommandFromResourceAssembler {
    public static RegisterAdministratorCommand toCommandFromResource(RegisterAdministratorResource resource) {
        return new RegisterAdministratorCommand(
                new PersonName(
                        resource.firstName(),
                        resource.lastName()
                ),
                new PhoneNumber(
                        resource.countryCode(),
                        resource.phone()
                ),
                new DniNumber(resource.dniNumber())
        );
    }
}
