package com.demy.platform.institution.domain.model.aggregates;

import com.demy.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.demy.platform.shared.domain.model.valueobjects.AcademyId;
import com.demy.platform.shared.domain.model.valueobjects.FullName;
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
    private AcademyId academyId;

    /**
     * Default constructor for JPA
     */
    protected Administrator() {}
}
