package com.demy.platform.institution.domain.model.events;

import com.demy.platform.shared.domain.model.valueobjects.AcademyId;
import com.demy.platform.shared.domain.model.valueobjects.DniNumber;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class AdministratorRegisteredEvent extends ApplicationEvent {

    private final AcademyId academyId;
    private final DniNumber dniNumber;

    public AdministratorRegisteredEvent(Object source, AcademyId academyId, DniNumber dniNumber) {
        super(source);
        this.academyId = academyId;
        this.dniNumber = dniNumber;
    }
}
