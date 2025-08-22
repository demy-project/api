package com.demy.platform.iam.application.internal.eventhandlers;

import com.demy.platform.iam.application.internal.outboundservices.email.NotificationEmailService;
import com.demy.platform.iam.domain.model.events.UserSignedUpEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
@Service
public class UserSignedUpEventHandler {

    private final NotificationEmailService notificationEmailService;

    public UserSignedUpEventHandler(NotificationEmailService notificationEmailService) {
        this.notificationEmailService = notificationEmailService;
    }

    @EventListener
    public void handle(UserSignedUpEvent event) {
        notificationEmailService.sendVerificationEmail(
                event.getUser().getEmailAddress().email(),
                event.getUser().getVerificationCode(),
                15
        );
    }
}
