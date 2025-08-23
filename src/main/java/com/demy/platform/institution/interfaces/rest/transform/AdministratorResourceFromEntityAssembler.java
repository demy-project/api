package com.demy.platform.institution.interfaces.rest.transform;

import com.demy.platform.institution.domain.model.aggregates.Administrator;
import com.demy.platform.institution.interfaces.rest.resources.AdministratorResource;

public class AdministratorResourceFromEntityAssembler {
    public static AdministratorResource toResourceFromEntity(Administrator entity) {
        return new AdministratorResource(
                entity.getId(),
                entity.getPersonName().firstName(),
                entity.getPersonName().lastName(),
                entity.getPhoneNumber().getFullNumber(),
                entity.getDniNumber().dniNumber(),
                entity.getAcademyId().academyId()
        );
    }
}
