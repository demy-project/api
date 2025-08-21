package com.demy.platform.iam.application.internal.outboundservices.email;

import java.util.Map;

public interface EmailService {

    void sendEmail(String to, String subject, String templateName, Map<String, Object> variables);
}
