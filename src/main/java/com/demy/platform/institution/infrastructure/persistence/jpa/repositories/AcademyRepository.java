package com.demy.platform.institution.infrastructure.persistence.jpa.repositories;

import com.demy.platform.institution.domain.model.aggregates.Academy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Academy entities.
 *
 * <p>
 * Extends JpaRepository to provide standard CRUD operations for the Academy aggregate
 * and allows for custom queries if needed.
 * </p>
 *
 * @since 1.0.0
 */
@Repository
public interface AcademyRepository extends JpaRepository<Academy, Long> {

}
