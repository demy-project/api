package com.demy.platform.iam.infrastructure.security.spring;

import java.util.Set;

public interface SpringSecurityCurrentUserProvider {
    Long getUserId();
    String getUsername();
    Set<String> getRoles();
    Long getTenantId();
}
