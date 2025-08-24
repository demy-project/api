package com.demy.platform.institution.domain.exceptions;

import com.demy.platform.shared.domain.exceptions.ErrorCodeSpec;

public enum InstitutionErrorCode implements ErrorCodeSpec {
    ADMINISTRATOR_ALREADY_EXISTS;

    @Override public String errorCode() { return name(); }
}
