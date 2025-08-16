package com.demy.platform.iam.infrastructure.security;

import java.util.Set;

public interface CurrentUserProvider {
    Long getUserId();
    String getUsername();
    Set<String> getRoles();
    Long getTenantId();
}
