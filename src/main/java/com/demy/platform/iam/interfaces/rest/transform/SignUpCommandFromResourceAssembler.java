package com.demy.platform.iam.interfaces.rest.transform;

import com.demy.platform.iam.domain.model.commands.SignUpCommand;
import com.demy.platform.iam.domain.model.entities.Role;
import com.demy.platform.iam.interfaces.rest.resources.SignUpResource;
import com.demy.platform.shared.domain.model.valueobjects.EmailAddress;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var roles = resource.roles() != null ? resource.roles().stream().map(name -> Role.toRoleFromName(name)).toList() : new ArrayList<Role>();
        return new SignUpCommand(new EmailAddress(resource.emailAddress()), resource.password(), roles);
    }
}
