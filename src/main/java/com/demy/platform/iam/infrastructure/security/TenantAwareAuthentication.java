package com.demy.platform.iam.infrastructure.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class TenantAwareAuthentication extends UsernamePasswordAuthenticationToken {

    private final Long tenantId;

    public TenantAwareAuthentication(Object principal,
                                     Object credentials,
                                     Collection<? extends GrantedAuthority> authorities,
                                     Long tenantId) {
        super(principal, credentials, authorities);
        this.tenantId = tenantId;
    }

    public Long getTenantId() {
        return tenantId;
    }
}
