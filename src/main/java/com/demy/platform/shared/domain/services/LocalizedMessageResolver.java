package com.demy.platform.shared.domain.services;

import java.util.Locale;

/**
 * Abstraction for resolving localized messages.
 *
 * @since 1.0.0
 */
public interface LocalizedMessageResolver {
    String getMessage(String code, Object[] args, Locale locale);
    String getMessage(String code, Locale locale);
}
