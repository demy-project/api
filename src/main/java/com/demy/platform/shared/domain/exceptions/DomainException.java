package com.demy.platform.shared.domain.exceptions;

import com.demy.platform.shared.domain.errors.DomainError;
import lombok.Getter;

@Getter
public abstract class DomainException extends RuntimeException {
    private final DomainError domainError;
    private final Object[] args;

    protected DomainException(DomainError domainError, Object... args) {
        super(domainError.name());
        this.domainError = domainError;
        this.args = args;
    }
}
