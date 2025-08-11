package com.demy.platform.institution.domain.model.aggregates;

import com.demy.platform.institution.domain.model.commands.RegisterAdministratorCommand;
import com.demy.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.demy.platform.shared.domain.model.valueobjects.*;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
public class Administrator extends AuditableAbstractAggregateRoot<Administrator> {

    @Embedded
    @Getter
    private FullName fullName;

    @Embedded
    @Getter
    private EmailAddress emailAddress;

    @Embedded
    @Getter
    private PhoneNumber phoneNumber;

    @Embedded
    @Getter
    private DniNumber dniNumber;

    @Embedded
    @Getter
    private AcademyId academyId;

    /**
     * Default constructor for JPA
     */
    protected Administrator() {}

    public Administrator(
            FullName fullName,
            EmailAddress emailAddress,
            PhoneNumber phoneNumber,
            DniNumber dniNumber,
            AcademyId academyId
    ) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.dniNumber = dniNumber;
        this.academyId = academyId;
    }

    public Administrator(RegisterAdministratorCommand command) {
        this(
                command.fullName(),
                command.emailAddress(),
                command.phoneNumber(),
                command.dniNumber(),
                command.academyId()
        );
    }
}
