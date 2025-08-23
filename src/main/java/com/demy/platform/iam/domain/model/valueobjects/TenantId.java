package com.demy.platform.iam.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record TenantId(
        @Column(unique = true)
        Long tenantId
) {
    public TenantId() {
        this(null);
    }

    public TenantId {
        if (tenantId != null && tenantId <= 0)
            throw new IllegalArgumentException("Tenant ID must be greater than zero if provided");
    }

    public boolean isAssigned() {
        return tenantId != null;
    }
}
