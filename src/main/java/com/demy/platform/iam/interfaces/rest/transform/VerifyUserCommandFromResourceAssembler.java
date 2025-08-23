package com.demy.platform.iam.interfaces.rest.transform;

import com.demy.platform.iam.domain.model.commands.VerifyUserCommand;
import com.demy.platform.iam.interfaces.rest.resources.VerifyUserResource;

public class VerifyUserCommandFromResourceAssembler {
    public static VerifyUserCommand toCommandFromResource(VerifyUserResource resource) {
        return new VerifyUserCommand(
                resource.email(),
                resource.code()
        );
    }
}
