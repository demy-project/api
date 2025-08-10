package com.demy.platform.institution.domain.model.commands;

import com.demy.platform.institution.domain.model.valueobjects.AdministratorId;
import com.demy.platform.institution.domain.model.valueobjects.Ruc;

/**
 * Command to register a new academy in the system.
 * <p>
 * Encapsulates the required data for registering an academy,
 * including the administrator's identifier and the academy's RUC.
 *
 * @author Salim Ramirez
 * @see IllegalArgumentException
 * @since 1.0.0
 */
public record RegisterAcademyCommand(
        AdministratorId administratorId,
        Ruc ruc
) {
    /**
     * Constructs a RegisterAcademyCommand with validation.
     *
     * @param administratorId the identifier of the administrator
     * @param ruc the RUC of the academy
     * @throws IllegalArgumentException if administratorId or ruc is null
     */
    public RegisterAcademyCommand {
        if (administratorId == null) {
            throw new IllegalArgumentException("AdministratorId cannot be null");
        }
        if (ruc == null) {
            throw new IllegalArgumentException("Ruc cannot be null");
        }
    }
}
