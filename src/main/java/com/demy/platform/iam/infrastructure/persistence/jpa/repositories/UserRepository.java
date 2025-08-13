package com.demy.platform.iam.infrastructure.persistence.jpa.repositories;

import com.demy.platform.iam.domain.model.aggregates.User;
import com.demy.platform.shared.domain.model.valueobjects.EmailAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAddress(EmailAddress emailAddress);

    boolean existsByEmailAddress(EmailAddress emailAddress);
}
