package com.demy.platform.institution.domain.model.aggregates;

import com.demy.platform.institution.domain.model.valueobjects.AdministratorId;
import com.demy.platform.institution.domain.model.valueobjects.Ruc;
import com.demy.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
public class Academy extends AuditableAbstractAggregateRoot<Academy> {

    @Getter
    @Embedded
    private AdministratorId administratorId;

    @Getter
    @Embedded
    private Ruc ruc;

    /**
     * Default constructor for JPA
     */
    protected Academy() {}
}
