package com.demy.platform.institution.application.internal.commandservices;

import com.demy.platform.institution.domain.model.aggregates.Academy;
import com.demy.platform.institution.domain.model.commands.RegisterAcademyCommand;
import com.demy.platform.institution.domain.services.AcademyCommandService;
import com.demy.platform.institution.infrastructure.persistence.jpa.repositories.AcademyRepository;
import org.springframework.stereotype.Service;

/**
 * Implementation of the {@link AcademyCommandService} interface for handling academy registration commands.
 * <p>
 * This service is responsible for processing commands related to academy registration,
 * including validation and persistence of the academy entity.
 *
 * @author Salim Ramirez
 * @see AcademyCommandService
 * @see RegisterAcademyCommand
 * @since 1.0.0
 */
@Service
public class AcademyCommandServiceImpl implements AcademyCommandService {

    private final AcademyRepository academyRepository;

    /**
     * Constructs an instance of the class.
     *
     * @param academyRepository the repository used for academy persistence operations
     */
    public AcademyCommandServiceImpl(AcademyRepository academyRepository) {
        this.academyRepository = academyRepository;
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method processes the provided command to create a new academy
     *
     * @param command the command containing the necessary data to register an academy
     * @return the identifier of the newly registered academy
     * @see RegisterAcademyCommand
     */
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
