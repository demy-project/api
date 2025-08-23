package com.demy.platform.iam.interfaces.rest.transform;

import com.demy.platform.iam.domain.model.commands.ResendVerificationCodeCommand;
import com.demy.platform.iam.interfaces.rest.resources.ResendVerificationCodeResource;

public class ResendVerificationCodeCommandFromResourceAssembler {
    public static ResendVerificationCodeCommand toCommandFromResource(ResendVerificationCodeResource resource) {
        return new ResendVerificationCodeCommand(
                resource.email()
        );
    }
}
