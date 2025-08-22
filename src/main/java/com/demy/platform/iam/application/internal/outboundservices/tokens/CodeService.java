package com.demy.platform.iam.application.internal.outboundservices.tokens;

import com.demy.platform.iam.domain.model.valueobjects.VerificationCode;

import java.time.LocalDateTime;

public interface CodeService {

    VerificationCode generateCode();

    String generateCode(int length);

    boolean verifyCode(String code, String expectedCode, LocalDateTime expirationTime);
}
