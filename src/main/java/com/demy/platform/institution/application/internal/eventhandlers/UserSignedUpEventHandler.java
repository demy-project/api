package com.demy.platform.institution.application.internal.eventhandlers;

import com.demy.platform.iam.domain.model.events.UserSignedUpEvent;
import com.demy.platform.institution.domain.services.AdministratorCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserSignedUpEventHandler {

    private final AdministratorCommandService administratorCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserSignedUpEventHandler.class);

    public UserSignedUpEventHandler(AdministratorCommandService administratorCommandService) {
        this.administratorCommandService = administratorCommandService;
    }

    @EventListener
    public void on(UserSignedUpEvent event) {
        var userId = event.getUserId();
        LOGGER.info("Handling UserSignedUpEvent for User ID {} at {}", userId, currentTimestamp());
        var roles = event.getRoles();
        LOGGER.info("User ID {} has roles: {}", userId, roles);
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
