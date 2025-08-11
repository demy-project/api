package com.demy.platform.institution.infrastructure.persistence.jpa.repositories;

import com.demy.platform.institution.domain.model.aggregates.Teacher;
import com.demy.platform.shared.domain.model.valueobjects.AcademyId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository for managing Teacher entities.
 *
 * <p>
 * Extends JpaRepository to provide standard CRUD operations,
 * and includes a custom method to retrieve teachers by academy ID.
 * </p>
 *
 * @since 1.0.0
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    /**
     * Retrieves a list of teachers associated with the specified academy.
     *
     * @param academyId the ID of the academy
     * @return a list of teachers belonging to the academy
     * @see AcademyId
     */
    List<Teacher> findAllByAcademyId(AcademyId academyId);
}
