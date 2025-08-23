package com.demy.platform.institution.domain.model.commands;

import com.demy.platform.institution.domain.model.valueobjects.AdministratorId;
import com.demy.platform.shared.domain.model.valueobjects.AcademyId;

public record AssignAdministratorToAcademyCommand(
        AcademyId academyId,
        AdministratorId administratorId
) {
    public AssignAdministratorToAcademyCommand {
        if (academyId == null)
            throw new IllegalArgumentException("Academy ID cannot be null");
        if (administratorId == null)
            throw new IllegalArgumentException("Administrator ID cannot be null");
    }
}
