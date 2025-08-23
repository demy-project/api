package com.demy.platform.iam.application.internal.outboundservices.identity;

import java.util.Optional;
import java.util.Set;

public interface IdentityService {

    Optional<Long> getUserId();

    Optional<String> getUsername();

    Set<String> getRoles();

    Optional<Long> getTenantId();
}
