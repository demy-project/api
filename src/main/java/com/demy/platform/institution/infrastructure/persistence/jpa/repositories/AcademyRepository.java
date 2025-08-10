package com.demy.platform.institution.infrastructure.persistence.jpa.repositories;

import com.demy.platform.institution.domain.model.aggregates.Academy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademyRepository extends JpaRepository<Academy, Long> {

}
