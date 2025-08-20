package com.demy.platform.institution.application.internal.eventhandlers;

import com.demy.platform.iam.application.internal.eventhandlers.ApplicationReadyEventHandler;
import com.demy.platform.institution.domain.model.commands.AssignAdministratorToAcademyCommand;
import com.demy.platform.institution.domain.model.events.AdministratorRegisteredEvent;
import com.demy.platform.institution.domain.model.queries.GetAcademyByIdQuery;
import com.demy.platform.institution.domain.model.queries.GetAdministratorByDniNumberQuery;
import com.demy.platform.institution.domain.model.valueobjects.AdministratorId;
import com.demy.platform.institution.domain.services.AcademyCommandService;
import com.demy.platform.institution.domain.services.AcademyQueryService;
import com.demy.platform.institution.domain.services.AdministratorQueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class AdministratorRegisteredEventHandler {

    private final AcademyCommandService academyCommandService;
    private final AcademyQueryService academyQueryService;
    private final AdministratorQueryService administratorQueryService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AdministratorRegisteredEventHandler.class);

    public AdministratorRegisteredEventHandler(
            AcademyCommandService academyCommandService,
            AcademyQueryService academyQueryService,
            AdministratorQueryService administratorQueryService
    ) {
        this.academyCommandService = academyCommandService;
        this.academyQueryService = academyQueryService;
        this.administratorQueryService = administratorQueryService;
    }

    @EventListener
    public void on(AdministratorRegisteredEvent event) {
        var getAcademyByIdQuery = new GetAcademyByIdQuery(event.getAcademyId().academyId());
        LOGGER.info("Handling AdministratorRegisteredEvent for Academy ID: {}", event.getAcademyId().academyId());
        var academy = academyQueryService.handle(getAcademyByIdQuery);
        LOGGER.info("Academy found: {}", academy.isPresent() ? "Yes" : "No");
        var administrator = administratorQueryService.handle(new GetAdministratorByDniNumberQuery(event.getDniNumber()));
        if (academy.isPresent() && administrator.isPresent()) {
            var assignAdministratorToAcademyCommand = new AssignAdministratorToAcademyCommand(
                    event.getAcademyId(),
                    new AdministratorId(administrator.get().getId()));
            LOGGER.info("Assigning Administrator ID: {} to Academy ID: {}", administrator.get().getId(), event.getAcademyId().academyId());
            academyCommandService.handle(assignAdministratorToAcademyCommand);
        }
    }
}
