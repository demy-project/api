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

    /**
     * Converts a message code to an error code by transforming it to uppercase and replacing dots with underscores.
     * @param messageCode the message code to convert (e.g., "error.something.happened")
     * @return the corresponding error code (e.g., "ERROR_SOMETHING_HAPPENED")
     */
    private String toErrorCode(String messageCode) {
        return messageCode.toUpperCase().replace('.', '_');
    }
}
