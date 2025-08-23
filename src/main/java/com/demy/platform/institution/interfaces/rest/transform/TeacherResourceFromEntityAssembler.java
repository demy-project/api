package com.demy.platform.institution.interfaces.rest.transform;

import com.demy.platform.institution.domain.model.aggregates.Teacher;
import com.demy.platform.institution.interfaces.rest.resources.TeacherResource;

public class TeacherResourceFromEntityAssembler {
    public static TeacherResource toResourceFromEntity(Teacher entity) {
        return new TeacherResource(
                entity.getPersonName().firstName(),
                entity.getPersonName().lastName(),
                entity.getAcademyId().academyId()
        );
    }
}
