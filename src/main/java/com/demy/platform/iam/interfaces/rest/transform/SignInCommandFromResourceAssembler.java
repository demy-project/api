package com.demy.platform.iam.interfaces.rest.transform;

import com.demy.platform.iam.domain.model.commands.SignInCommand;
import com.demy.platform.iam.interfaces.rest.resources.SignInResource;
import com.demy.platform.shared.domain.model.valueobjects.EmailAddress;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(new EmailAddress(signInResource.emailAddress()), signInResource.password());
    }
}
