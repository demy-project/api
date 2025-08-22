package com.demy.platform.iam.application.internal.outboundservices.verification;

import com.demy.platform.iam.domain.model.valueobjects.VerificationCode;

import java.time.LocalDateTime;

public interface VerificationService {

    VerificationCode generateCode();

    String generateCode(int length);

    boolean verifyCode(String code, String expectedCode, LocalDateTime expirationTime);
}
