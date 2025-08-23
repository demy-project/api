package com.demy.platform.shared.domain.exceptions;

import lombok.Getter;

@Getter
public abstract class DomainException extends RuntimeException {
    private final String messageCode;
    private final String errorCode;
    private final Object[] args;
    protected DomainException(String messageCode, Object... args) {
        super(messageCode);
        this.messageCode = messageCode;
        this.errorCode = toErrorCode(messageCode);
        this.args = args;
    }

    private String toErrorCode(String messageCode) {
        return messageCode.toUpperCase().replace('.', '_');
    }
}
