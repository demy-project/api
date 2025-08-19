package com.demy.platform.institution.domain.model.aggregates;

import com.demy.platform.institution.domain.model.valueobjects.UserId;
import com.demy.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.demy.platform.shared.domain.model.valueobjects.AcademyId;
import com.demy.platform.shared.domain.model.valueobjects.PersonName;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
public class Teacher extends AuditableAbstractAggregateRoot<Teacher> {

    @Embedded
    @Getter
    private PersonName personName;

    @Embedded
    @Getter
    private AcademyId academyId;

    @Embedded
    @Getter
    private UserId userId;

    /**
     * Default constructor for JPA
     */
    protected Teacher() {}
}
