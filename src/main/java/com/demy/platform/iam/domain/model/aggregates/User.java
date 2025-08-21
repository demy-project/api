package com.demy.platform.iam.domain.model.aggregates;

import com.demy.platform.iam.domain.model.entities.Role;
import com.demy.platform.iam.domain.model.events.UserSignedUpEvent;
import com.demy.platform.iam.domain.model.valueobjects.AccountStatus;
import com.demy.platform.iam.domain.model.valueobjects.TenantId;
import com.demy.platform.iam.domain.model.valueobjects.VerificationStatus;
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

    @Enumerated(EnumType.STRING)
    private VerificationStatus verificationStatus;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    private String verificationCode;

    private LocalDateTime verificationCodeExpiresAt;

    @Embedded
    private TenantId tenantId;

    public User() {}

    public User(EmailAddress emailAddress, String password, String verificationCode, LocalDateTime verificationCodeExpiresAt) {
        this.emailAddress = emailAddress;
        this.password = password;
        this.verificationCode = verificationCode;
        this.verificationCodeExpiresAt = verificationCodeExpiresAt;
        this.verificationStatus = VerificationStatus.NOT_VERIFIED;
        this.accountStatus = AccountStatus.PENDING;
        this.roles = new HashSet<>();
        this.tenantId = new TenantId();
    }

    public User(EmailAddress emailAddress, String password, String verificationCode, LocalDateTime verificationCodeExpiresAt, List<Role> roles) {
        this(emailAddress, password, verificationCode, verificationCodeExpiresAt);
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

    public boolean isVerified() {
        return verificationStatus == VerificationStatus.VERIFIED;
    }

    public void verify() {
        if (verificationStatus != VerificationStatus.NOT_VERIFIED)
            throw new IllegalStateException("User is already verified or in an invalid state");
        this.verificationCode = null;
        this.verificationCodeExpiresAt = null;
        this.verificationStatus = VerificationStatus.VERIFIED;
    }

    public void activate() {
        if (verificationStatus != VerificationStatus.VERIFIED)
            throw new IllegalStateException("User must be verified before activation");
        this.accountStatus = AccountStatus.ACTIVE;
    }

    public void assignNewVerificationCode(String verificationCode, LocalDateTime verificationCodeExpiresAt) {
        this.verificationCode = verificationCode;
        this.verificationCodeExpiresAt = verificationCodeExpiresAt;
    }

    public void registerSignUpUser(User user, List<Role> roles) {
        this.addDomainEvent(new UserSignedUpEvent(this, user, roles));
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

    public Long getTenantIdOrNull() {
        return (tenantId != null && tenantId.isAssigned()) ? tenantId.tenantId() : null;
    }
}
