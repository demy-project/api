package com.demy.platform.iam.infrastructure.verification.otp.services;

import com.demy.platform.iam.infrastructure.verification.otp.OtpSecureVerificationService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class VerificationServiceImpl implements OtpSecureVerificationService {

    private final SecureRandom secureRandom = new SecureRandom();

    @Override
    public String generateCode() {
        int max = (int) Math.pow(10, 6) - 1;
        int code = secureRandom.nextInt(max + 1);
        return String.format("%06d", code);
    }

    @Override
    public String generateCode(int length) {
        int max = (int) Math.pow(10, length) - 1;
        int code = secureRandom.nextInt(max + 1);
        return String.format("%0" + length + "d", code);
    }

    @Override
    public Integer generateExpirationMinutes() {
        return 15;
    }

    @Override
    public boolean verifyCode(String code, String expectedCode, LocalDateTime expirationTime) {
        if (code == null || expectedCode == null || expirationTime == null) return false;
        if (LocalDateTime.now().isAfter(expirationTime)) return false; // Code has expired
        return code.equals(expectedCode); // Check if the code matches
    }
}