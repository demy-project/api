package com.demy.platform.iam.infrastructure.tokens.verification.services;

import com.demy.platform.iam.domain.model.valueobjects.VerificationCode;
import com.demy.platform.iam.infrastructure.tokens.verification.VerificationCodeService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class SecureVerificationCodeServiceImpl implements VerificationCodeService {

    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public VerificationCode generateCode() {
        int max = (int) Math.pow(10, 6) - 1;
        int code = secureRandom.nextInt(max + 1);
        int expirationMinutes = 15;
        return new VerificationCode(String.format("%06d", code), LocalDateTime.now().plusMinutes(expirationMinutes));
    }

    @Override
    public String generateCode(int length) {
        int max = (int) Math.pow(10, length) - 1;
        int code = secureRandom.nextInt(max + 1);
        return String.format("%0" + length + "d", code);
    }

    @Override
    public boolean verifyCode(String code, String expectedCode, LocalDateTime expirationTime) {
        if (code == null || expectedCode == null || expirationTime == null) return false;
        if (LocalDateTime.now().isAfter(expirationTime)) return false; // Code has expired
        return code.equals(expectedCode); // Check if the code matches
    }
}