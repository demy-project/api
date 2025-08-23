package com.demy.platform.iam.interfaces.rest.transform;

import com.demy.platform.iam.domain.model.aggregates.User;
import com.demy.platform.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getEmailAddress().email(), token);
    }
}
