package com.demy.platform.shared.domain.exceptions;

public enum ErrorCode implements ErrorCodeSpec {
    BAD_REQUEST("error.bad_request"),
    VALIDATION("error.validation"),
    RUNTIME("error.runtime"),
    UNEXPECTED("error.unexpected");

    private final String messageCode;

    ErrorCode(String messageCode) { this.messageCode = messageCode; }

    @Override public String errorCode() { return name(); }
}
