package com.demy.platform.institution.domain.model.aggregates;

import com.demy.platform.institution.domain.model.commands.RegisterAcademyCommand;
import com.demy.platform.institution.domain.model.valueobjects.AdministratorId;
import com.demy.platform.institution.domain.model.valueobjects.Ruc;
import com.demy.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

/**
 * Represents an Academy entity in the domain model.
 * <p>
 * This class extends {@link AuditableAbstractAggregateRoot} to provide auditing capabilities.
 * It contains the administrator ID and RUC (Registro Único de Contribuyentes) as embedded value objects.
 *
 * @author Salim Ramirez
 * @see AuditableAbstractAggregateRoot
 * @see AdministratorId
 * @see Ruc
 * @since 1.0.0
 */
@Entity
public class Academy extends AuditableAbstractAggregateRoot<Academy> {

    @Embedded
    @Getter
    private AdministratorId administratorId;

    @Embedded
    @Getter
    private Ruc ruc;

    /**
     * Default constructor for JPA
     */
    protected Academy() {}

    /**
     * Constructs an Academy instance with the specified administrator ID and RUC.
     *
     * @param administratorId the ID of the administrator
     * @param ruc the RUC of the academy
     * @see AdministratorId
     * @see Ruc
     */
    public Academy(AdministratorId administratorId, Ruc ruc) {
        this.administratorId = administratorId;
        this.ruc = ruc;
    }

    /**
     * Constructs an Academy instance from a RegisterAcademyCommand.
     *
     * @param command the command containing the necessary data to register an academy
     * @see RegisterAcademyCommand
     */
    public Academy(RegisterAcademyCommand command) {
        this(command.administratorId(), command.ruc());
    }
}
