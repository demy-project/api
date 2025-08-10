package com.demy.platform.institution.infrastructure.persistence.jpa.repositories;

import com.demy.platform.institution.domain.model.aggregates.Teacher;
import com.demy.platform.shared.domain.model.valueobjects.AcademyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findAllByAcademyId(AcademyId academyId);
}
