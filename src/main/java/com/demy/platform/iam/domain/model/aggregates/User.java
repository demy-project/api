package com.demy.platform.iam.domain.model.aggregates;

import com.demy.platform.iam.domain.model.entities.Role;
import com.demy.platform.iam.domain.model.events.UserSignedUpEvent;
import com.demy.platform.iam.domain.model.valueobjects.TenantId;
import com.demy.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.demy.platform.shared.domain.model.valueobjects.EmailAddress;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Getter
@Setter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {

    @Embedded
    private EmailAddress emailAddress;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    private boolean verified = false;

    private String verificationCode;

    private LocalDateTime verificationCodeExpiresAt;

    @Embedded
    private TenantId tenantId;

    public User() {}

    public User(EmailAddress emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.roles = new HashSet<>();
        this.tenantId = new TenantId();
    }

    public User(EmailAddress emailAddress, String password, List<Role> roles) {
        this(emailAddress, password);
        addRoles(roles);
    }

    public User addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    @SuppressWarnings("UnusedReturnValue")
    public User addRoles(List<Role> roles) {
        var validateRoleSet = Role.validateRoleSet(roles);
        this.roles.addAll(validateRoleSet);
        return this;
    }

    public void generateVerificationCode(int expirationMinutes) {
        this.verificationCode = String.format("%06d", new Random().nextInt(999999));
        this.verificationCodeExpiresAt = LocalDateTime.now().plusMinutes(expirationMinutes);
    }

    public boolean verifyCode(String code) {
        if (verificationCode == null || verificationCodeExpiresAt == null) return false;
        if (LocalDateTime.now().isAfter(verificationCodeExpiresAt)) return false;
        if (!verificationCode.equals(code)) return false;

        this.verified = true;
        this.verificationCode = null;
        this.verificationCodeExpiresAt = null;
        return true;
    }

    public void registerSignUpUser(User user, List<Role> roles) {
        this.addDomainEvent(new UserSignedUpEvent(this, user, roles));
    }

    public Long getTenantIdOrNull() {
        return (tenantId != null && tenantId.isAssigned()) ? tenantId.tenantId() : null;
    }

    public void associateTenant(TenantId tenantId) {
        if (this.tenantId != null && this.tenantId.isAssigned())
            throw new IllegalStateException("User is already associated with a tenant");
        this.tenantId = tenantId;
    }

    public void disassociateTenant(TenantId tenantId) {
        if (this.tenantId == null || !this.tenantId.equals(tenantId)) {
            throw new IllegalStateException("User is not associated with the provided tenant");
        }
        this.tenantId = new TenantId();
    }
}
