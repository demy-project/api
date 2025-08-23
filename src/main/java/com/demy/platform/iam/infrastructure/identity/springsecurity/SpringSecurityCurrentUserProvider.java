package com.demy.platform.iam.infrastructure.identity.springsecurity;

import java.util.Set;

public interface SpringSecurityCurrentUserProvider {
    Long getUserId();
    String getUsername();
    Set<String> getRoles();
    Long getTenantId();
}
