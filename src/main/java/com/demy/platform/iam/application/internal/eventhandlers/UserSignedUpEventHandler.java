package com.demy.platform.iam.application.internal.eventhandlers;

import com.demy.platform.iam.application.internal.outboundservices.email.NotificationEmailService;
import com.demy.platform.iam.domain.model.events.UserSignedUpEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserSignedUpEventHandler {

    private final NotificationEmailService notificationEmailService;

    public UserSignedUpEventHandler(NotificationEmailService notificationEmailService) {
        this.notificationEmailService = notificationEmailService;
    }

    @EventListener
    public void handle(UserSignedUpEvent event) {
        Map<String, Object> variables = Map.of(
                "code", event.getUser().getVerificationCode(),
                "expirationMinutes", 15
        );

        notificationEmailService.sendEmail(
                event.getUser().getEmailAddress().email(),
                "Confirma tu cuenta en Demy",
                "verification-email",
                variables
        );
    }
}
