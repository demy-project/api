package com.demy.platform.shared.interfaces.rest.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.demy.platform.shared.domain.exceptions.DomainException;
import com.demy.platform.shared.application.internal.outboundservices.localization.LocalizationService;
import com.demy.platform.shared.interfaces.rest.resources.ErrorResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private final LocalizationService localizationService;

    public GlobalExceptionHandler(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    /**
     * Handle domain-specific exceptions (business rules)
     */
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResource> handleDomainException(DomainException ex, Locale locale, WebRequest request) {
        LOGGER.info("Resolved locale: {}", locale);
        String path = extractPath(request);
        String message = localizationService.getMessage(ex.getMessageCode(), ex.getArgs(), locale);
        LOGGER.warn("Domain exception: {} at {}", ex.getMessageCode(), path, ex);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getErrorCode(), message, path);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResource> handleIllegalArgumentException(IllegalArgumentException ex, Locale locale, WebRequest request) {
        String path = extractPath(request);
        String message = localizationService.getMessage("error.bad_request", null, locale);
        LOGGER.warn("Illegal argument exception at {}: {}", path, ex.getMessage(), ex);

        return buildErrorResponse(HttpStatus.BAD_REQUEST, "error.bad_request", message, path);
    }

    /**
     * Handle unexpected exceptions (system errors)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResource> handleGenericException(Exception ex, Locale locale, WebRequest request) {
        String path = extractPath(request);
        String message = localizationService.getMessage("error.unexpected", null, locale);
        LOGGER.error("Unexpected exception at {}: {}", path, ex.getMessage(), ex);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "error.unexpected", message, path);
    }

    private ResponseEntity<ErrorResource> buildErrorResponse(HttpStatus status, String errorCode, String message, String path) {
        ErrorResource errorResource = new ErrorResource(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                errorCode,
                message,
                path
        );
        return new ResponseEntity<>(errorResource, status);
    }

    private String extractPath(WebRequest request) {
        return request.getDescription(false).replace("uri=", "");
    }
}