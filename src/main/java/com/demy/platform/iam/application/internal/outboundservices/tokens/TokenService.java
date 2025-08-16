package com.demy.platform.iam.application.internal.outboundservices.tokens;

public interface TokenService {

    String generateToken(String username);

    String generateToken(String username, Long academyId);

    String getUsernameFromToken(String token);

    Long getUserIdFromToken(String token);

    Long getTenantIdFromToken(String token);

    boolean validateToken(String token);
}
