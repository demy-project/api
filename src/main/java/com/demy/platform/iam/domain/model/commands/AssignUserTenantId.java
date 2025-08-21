package com.demy.platform.iam.domain.model.commands;

public record AssignUserTenantId(
        Long userId,
        Long tenantId
) {
}
