package com.demy.platform.institution.application.internal.commandservices;

import com.demy.platform.institution.domain.model.aggregates.Academy;
import com.demy.platform.institution.domain.model.commands.RegisterAcademyCommand;
import com.demy.platform.institution.domain.services.AcademyCommandService;
import com.demy.platform.institution.infrastructure.persistence.jpa.repositories.AcademyRepository;
import org.springframework.stereotype.Service;

@Service
public class AcademyCommandServiceImpl implements AcademyCommandService {

    private final AcademyRepository academyRepository;

    public AcademyCommandServiceImpl(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }

    @Override
    public Long handle(RegisterAcademyCommand command) {
        if (academyRepository.existsByRuc(command.ruc()))
            throw new IllegalArgumentException("An academy with RUC %s already exists".formatted(command.ruc().ruc()));
        var academy = new Academy(command);
        try {
            academyRepository.save(academy);
            return academy.getId();
        } catch (Exception e) {
            throw new RuntimeException("Failed to register academy: %s".formatted(e.getMessage()));
        }
    }
}
