package com.demy.platform.institution.application.internal.queryservices;

import com.demy.platform.institution.domain.model.aggregates.Administrator;
import com.demy.platform.institution.domain.model.queries.GetAdministratorByDniNumberQuery;
import com.demy.platform.institution.domain.services.AdministratorQueryService;
import com.demy.platform.institution.infrastructure.persistence.jpa.repositories.AdministratorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministratorQueryServiceImpl implements AdministratorQueryService {

    private final AdministratorRepository administratorRepository;

    public AdministratorQueryServiceImpl(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @Override
    public Optional<Administrator> handle(GetAdministratorByDniNumberQuery query) {
        return administratorRepository.findByDniNumber(query.dniNumber());
    }
}
