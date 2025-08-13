package com.demy.platform.iam.application.internal.outboundservices.tokens;

public interface TokenService {

    String generateToken(String emailAddress);

    String getEmailAddressFromToken(String token);

    boolean validateToken(String token);
}
