package com.demy.platform.iam.domain.services;

import com.demy.platform.iam.domain.model.aggregates.User;
import com.demy.platform.iam.domain.model.commands.*;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {

    Optional<ImmutablePair<User, String>> handle(SignInCommand command);

    Optional<User> handle(SignUpCommand command);

    boolean handle(VerifyUserCommand command);

    boolean handle(ResendVerificationCodeCommand command);

    void handle(AssignUserTenantId command);
}
