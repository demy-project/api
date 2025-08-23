package com.demy.platform.institution.application.internal.queryservices;

import com.demy.platform.iam.infrastructure.security.spring.SpringSecurityCurrentUserProvider;
import com.demy.platform.institution.domain.model.aggregates.Teacher;
import com.demy.platform.institution.domain.model.queries.GetAllTeachersQuery;
import com.demy.platform.institution.domain.services.TeacherQueryService;
import com.demy.platform.institution.infrastructure.persistence.jpa.repositories.TeacherRepository;
import com.demy.platform.shared.domain.model.valueobjects.AcademyId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherQueryServiceImpl implements TeacherQueryService {

    private final TeacherRepository teacherRepository;
    private final SpringSecurityCurrentUserProvider currentUserProvider;

    public TeacherQueryServiceImpl(TeacherRepository teacherRepository, SpringSecurityCurrentUserProvider currentUserProvider) {
        this.teacherRepository = teacherRepository;
        this.currentUserProvider = currentUserProvider;
    }

    @Override
    public List<Teacher> handle(GetAllTeachersQuery query) {
        var academyId = currentUserProvider.getTenantId();
        return teacherRepository.findAllByAcademyId(new AcademyId(academyId));
    }
}
