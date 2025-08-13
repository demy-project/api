package com.demy.platform.iam.infrastructure.tokens.jwt;

import com.demy.platform.iam.application.internal.outboundservices.tokens.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface BearerTokenService extends TokenService {

    String getBearerToken(HttpServletRequest token);

    String generateToken(Authentication authentication);
}
