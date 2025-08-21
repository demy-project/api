package com.demy.platform.iam.application.internal.commandservices;

import com.demy.platform.iam.application.internal.outboundservices.hashing.HashingService;
import com.demy.platform.iam.application.internal.outboundservices.tokens.TokenService;
import com.demy.platform.iam.domain.exceptions.UserNotFoundException;
import com.demy.platform.iam.domain.model.aggregates.User;
import com.demy.platform.iam.domain.model.commands.AssignUserTenantId;
import com.demy.platform.iam.domain.model.commands.SignInCommand;
import com.demy.platform.iam.domain.model.commands.SignUpCommand;
import com.demy.platform.iam.domain.model.valueobjects.Roles;
import com.demy.platform.iam.domain.model.valueobjects.TenantId;
import com.demy.platform.iam.domain.services.UserCommandService;
import com.demy.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.demy.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;

    private final RoleRepository roleRepository;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByEmailAddress(command.emailAddress());
        if (user.isEmpty())
            throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getEmailAddress().email());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByEmailAddress(command.emailAddress()))
            throw new RuntimeException("Username already exists");
        var roles = (command.roles() == null || command.roles().isEmpty())
                ? List.of(roleRepository.findByName(Roles.ROLE_USER).orElseThrow(() -> new RuntimeException("Default role not found")))
                : command.roles().stream()
                .map(role -> roleRepository.findByName(role.getName()).orElseThrow(() -> new RuntimeException("Role name not found")))
                .toList();
        var user = new User(command.emailAddress(), hashingService.encode(command.password()), roles);
        user.registerSignUpUser(roles);
        userRepository.save(user);
        return userRepository.findByEmailAddress(command.emailAddress());
    }

    @Override
    public void handle(AssignUserTenantId command) {
        var user = userRepository.findById(command.userId())
                .orElseThrow(() -> new UserNotFoundException(command.userId()));
        try {
            user.associateTenant(new TenantId(command.tenantId()));
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Failed to assign tenant to user: %s".formatted(e.getMessage()));
        }
    }
}
