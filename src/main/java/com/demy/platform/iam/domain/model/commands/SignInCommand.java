package com.demy.platform.iam.domain.model.commands;

import com.demy.platform.shared.domain.model.valueobjects.EmailAddress;

public record SignInCommand(
        EmailAddress emailAddress,
        String password
) {
}
