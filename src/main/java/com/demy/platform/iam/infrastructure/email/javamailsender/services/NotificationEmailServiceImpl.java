package com.demy.platform.iam.infrastructure.email.javamailsender.services;

import com.demy.platform.iam.infrastructure.email.javamailsender.UserNotificationEmailService;
import com.demy.platform.shared.infrastructure.email.javamailsender.TemplatedEmailService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationEmailServiceImpl implements UserNotificationEmailService {

    private final TemplatedEmailService templatedEmailService;

    public NotificationEmailServiceImpl(TemplatedEmailService templatedEmailService) {
        this.templatedEmailService = templatedEmailService;
    }

    @Override
    public void sendVerificationEmail(String to, String code, int expirationMinutes) {
        sendTemplatedEmail(to, "Confirm your Demy account", "email/verification-email",
                Map.of("code", code, "expiration", expirationMinutes));
    }

    private void sendTemplatedEmail(String to, String subject, String template, Map<String, Object> variables) {
        templatedEmailService.sendEmail(to, subject, template, variables);
    }
}
