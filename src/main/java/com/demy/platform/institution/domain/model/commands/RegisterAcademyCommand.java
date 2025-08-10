package com.demy.platform.institution.domain.model.commands;

import com.demy.platform.institution.domain.model.valueobjects.AdministratorId;
import com.demy.platform.institution.domain.model.valueobjects.Ruc;

public record RegisterAcademyCommand(
        AdministratorId administratorId,
        Ruc ruc
) {
    public RegisterAcademyCommand {
        if (administratorId == null) {
            throw new IllegalArgumentException("AdministratorId cannot be null");
        }
        if (ruc == null) {
            throw new IllegalArgumentException("Ruc cannot be null");
        }
    }
}
