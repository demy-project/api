package com.demy.platform.iam.infrastructure.security.spring.services;

import com.demy.platform.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import com.demy.platform.iam.infrastructure.security.spring.SpringSecurityCurrentUserProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CurrentUserProviderImpl implements SpringSecurityCurrentUserProvider {

    @Override
    public Long getUserId() {
        var principal = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return principal.getUserId();
    }

    @Override
    public String getUsername() {
        var principal = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return principal.getUsername();
    }

    @Override
    public Set<String> getRoles() {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .map(a -> a.getAuthority())
                .collect(Collectors.toSet());
    }

    @Override
    public Long getTenantId() {
        var principal = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return principal.getTenantId();
    }
}
