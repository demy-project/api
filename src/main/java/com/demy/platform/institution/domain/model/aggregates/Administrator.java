package com.demy.platform.institution.domain.model.aggregates;

import com.demy.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.demy.platform.shared.domain.model.valueobjects.AcademyId;
import com.demy.platform.shared.domain.model.valueobjects.FullName;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
public class Administrator extends AuditableAbstractAggregateRoot<Administrator> {

    @Embedded
    private FullName fullName;

    @Embedded
    private AcademyId academyId;

    /**
     * Default constructor for JPA
     */
    protected Administrator() {}
}
