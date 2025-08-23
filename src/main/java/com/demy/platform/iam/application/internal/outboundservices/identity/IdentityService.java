package com.demy.platform.iam.application.internal.outboundservices.identity;

import java.util.Optional;
import java.util.Set;

public interface IdentityService {

    Long getUserId();

    String getUsername();

    Set<String> getRoles();

    Optional<Long> getTenantId();
}
