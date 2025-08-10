package com.demy.platform.institution.domain.services;

import com.demy.platform.institution.domain.model.commands.RegisterAcademyCommand;

public interface AcademyCommandService {

    Long handle(RegisterAcademyCommand command);
}
