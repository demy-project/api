package com.demy.platform.institution.domain.services;

import com.demy.platform.institution.domain.model.commands.RegisterAdministratorCommand;

public interface AdministratorCommandService {

    Long handle(RegisterAdministratorCommand command);
}
