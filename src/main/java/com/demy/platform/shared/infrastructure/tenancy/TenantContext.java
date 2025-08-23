package com.demy.platform.shared.infrastructure.tenancy;

public final class TenantContext {

    private static final ThreadLocal<Long> TENANT_ID_HOLDER = new ThreadLocal<>();

    private TenantContext() {
        // Private constructor to prevent instantiation
    }

    public static void setTenantId(Long tenantId) {
        TENANT_ID_HOLDER.set(tenantId);
    }

    public static Long getTenantId() {
        return TENANT_ID_HOLDER.get();
    }

    public static void clear() {
        TENANT_ID_HOLDER.remove();
    }
}
