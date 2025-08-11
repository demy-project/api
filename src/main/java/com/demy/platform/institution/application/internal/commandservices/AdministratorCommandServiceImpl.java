package com.demy.platform.institution.application.internal.commandservices;

import com.demy.platform.institution.domain.model.aggregates.Administrator;
import com.demy.platform.institution.domain.model.commands.RegisterAdministratorCommand;
import com.demy.platform.institution.domain.services.AdministratorCommandService;
import com.demy.platform.institution.infrastructure.persistence.jpa.repositories.AdministratorRepository;
import org.springframework.stereotype.Service;

@Service
public class AdministratorCommandServiceImpl implements AdministratorCommandService {

    private final AdministratorRepository administratorRepository;

    public AdministratorCommandServiceImpl(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @Override
    public Long handle(RegisterAdministratorCommand command) {
        if (administratorRepository.existsByDniNumber(command.dniNumber()))
            throw new IllegalArgumentException("An administrator with DNI %s already exists".formatted(command.dniNumber().dniNumber()));
        var administrator = new Administrator(command);
        try {
            administratorRepository.save(administrator);
            return administrator.getId();
        } catch (Exception e) {
            throw new RuntimeException("Failed to register administrator: %s".formatted(e.getMessage()));
        }
    }
}
