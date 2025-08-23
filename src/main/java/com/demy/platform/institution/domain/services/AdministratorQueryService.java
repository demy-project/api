package com.demy.platform.institution.domain.services;

import com.demy.platform.institution.domain.model.aggregates.Administrator;
import com.demy.platform.institution.domain.model.queries.GetAdministratorByDniNumberQuery;

import java.util.Optional;

public interface AdministratorQueryService {

    Optional<Administrator> handle(GetAdministratorByDniNumberQuery query);
}
