package com.demy.platform.iam.infrastructure.persistence.jpa.repositories;

import com.demy.platform.iam.domain.model.entities.Role;
import com.demy.platform.iam.domain.model.valueobjects.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(Roles name);
}
