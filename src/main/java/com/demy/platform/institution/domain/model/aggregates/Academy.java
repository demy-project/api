package com.demy.platform.institution.domain.model.aggregates;

import com.demy.platform.institution.domain.model.commands.RegisterAcademyCommand;
import com.demy.platform.institution.domain.model.valueobjects.AcademyDescription;
import com.demy.platform.institution.domain.model.valueobjects.AcademyName;
import com.demy.platform.institution.domain.model.valueobjects.AdministratorId;
import com.demy.platform.institution.domain.model.valueobjects.Ruc;
import com.demy.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.demy.platform.shared.domain.model.valueobjects.EmailAddress;
import com.demy.platform.shared.domain.model.valueobjects.PhoneNumber;
import com.demy.platform.shared.domain.model.valueobjects.StreetAddress;
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
    private AcademyName academyName;

    @Embedded
    @Getter
    private AcademyDescription academyDescription;

    @Embedded
    @Getter
    private StreetAddress streetAddress;

    @Embedded
    @Getter
    private EmailAddress emailAddress;

    @Embedded
    @Getter
    private PhoneNumber phoneNumber;

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
    public Academy(AdministratorId administratorId,
                   AcademyName academyName,
                   AcademyDescription academyDescription,
                   StreetAddress streetAddress,
                   EmailAddress emailAddress,
                   PhoneNumber phoneNumber,
                   Ruc ruc) {
        this.administratorId = administratorId;
        this.academyName = academyName;
        this.academyDescription = academyDescription;
        this.streetAddress = streetAddress;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.ruc = ruc;
    }

    /**
     * Constructs an Academy instance from a RegisterAcademyCommand.
     *
     * @param command the command containing the necessary data to register an academy
     * @see RegisterAcademyCommand
     */
    public Academy(RegisterAcademyCommand command) {
        this(
                command.administratorId(),
                command.academyName(),
                command.academyDescription(),
                command.streetAddress(),
                command.emailAddress(),
                command.phoneNumber(),
                command.ruc()
        );
    }
}
