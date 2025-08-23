package com.demy.platform.iam.application.internal.outboundservices.email;

public interface EmailService {

    void sendVerificationEmail(String to, String token, int expirationMinutes);

    void sendPasswordResetEmail(String to, String resetLink);
}
