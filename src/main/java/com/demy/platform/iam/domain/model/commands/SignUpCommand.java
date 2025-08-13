package com.demy.platform.iam.domain.model.commands;

import com.demy.platform.iam.domain.model.entities.Role;
import com.demy.platform.shared.domain.model.valueobjects.EmailAddress;

import java.util.List;

public record SignUpCommand(
        EmailAddress emailAddress,
        String password,
        List<Role> roles
) {
}
