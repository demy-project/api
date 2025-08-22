package com.demy.platform.iam.domain.model.commands;

public record VerifyUserCommand(
        String email,
        String code
) {

}
