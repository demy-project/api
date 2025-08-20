package com.demy.platform.institution.application.internal.commandservices;

import com.demy.platform.institution.domain.model.aggregates.Administrator;
import com.demy.platform.institution.domain.model.commands.RegisterAdministratorCommand;
import com.demy.platform.institution.domain.model.valueobjects.AdministratorId;
import com.demy.platform.institution.domain.services.AdministratorCommandService;
import com.demy.platform.institution.infrastructure.persistence.jpa.repositories.AdministratorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdministratorCommandServiceImpl implements AdministratorCommandService {

    private final AdministratorRepository administratorRepository;

    public AdministratorCommandServiceImpl(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @Override
    public Optional<Administrator> handle(RegisterAdministratorCommand command) {
        if (administratorRepository.existsByDniNumber(command.dniNumber()))
            throw new IllegalArgumentException("An administrator with DNI %s already exists".formatted(command.dniNumber().dniNumber()));
        var administrator = new Administrator(command);
        try {
            administrator.associateAcademy(command.academyId(), command.dniNumber());
            administratorRepository.save(administrator);
            return Optional.of(administrator);
        } catch (Exception e) {
            throw new RuntimeException("Failed to register administrator: %s".formatted(e.getMessage()));
        }
    }
}
