package com.demy.platform.iam.application.internal.outboundservices.tokens;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.time.LocalDateTime;

public interface CodeService {

    ImmutablePair<String, LocalDateTime> generateCode();

    String generateCode(int length);

    boolean verifyCode(String code, String expectedCode, LocalDateTime expirationTime);
}
