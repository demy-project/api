package com.demy.platform.institution.domain.model.aggregates;

import com.demy.platform.institution.domain.model.commands.RegisterAdministratorCommand;
import com.demy.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.demy.platform.shared.domain.model.valueobjects.*;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

    @ElementCollection
    @CollectionTable(
            name = "administrator_academies",
            joinColumns = @JoinColumn(name = "administrator_id", referencedColumnName = "id")
    )
    private Set<AcademyId> academyIds = new HashSet<>();

    /**
     * Default constructor for JPA
     */
    protected Administrator() {}

    public Administrator(
            FullName fullName,
            EmailAddress emailAddress,
            PhoneNumber phoneNumber,
            DniNumber dniNumber,
            Set<AcademyId> academyIds
    ) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.dniNumber = dniNumber;
        this.academyIds = academyIds != null ? new HashSet<>(academyIds) : new HashSet<>();
    }

    public Administrator(RegisterAdministratorCommand command) {
        this(
                command.fullName(),
                command.emailAddress(),
                command.phoneNumber(),
                command.dniNumber(),
                command.academyIds()
        );
    }

    public void associateAcademy(AcademyId academyId) {
        this.academyIds.add(academyId);
    }

    public void disassociateAcademy(AcademyId academyId) {
        this.academyIds.remove(academyId);
    }

    public Set<AcademyId> getAcademyIds() {
        return Collections.unmodifiableSet(academyIds);
    }
}
