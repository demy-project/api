package com.demy.platform.institution.domain.model.aggregates;

import com.demy.platform.institution.domain.model.commands.RegisterAcademyCommand;
import com.demy.platform.institution.domain.model.valueobjects.AdministratorId;
import com.demy.platform.institution.domain.model.valueobjects.Ruc;
import com.demy.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

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

    public Academy(AdministratorId administratorId, Ruc ruc) {
        this.administratorId = administratorId;
        this.ruc = ruc;
    }

    public Academy(RegisterAcademyCommand command) {
        this(command.administratorId(), command.ruc());
    }
}
