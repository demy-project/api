package com.demy.platform.shared.interfaces.rest.exceptions;

import java.util.Locale;

import com.demy.platform.shared.domain.exceptions.DomainException;
import com.demy.platform.shared.application.internal.outboundservices.localization.LocalizationService;
import com.demy.platform.shared.interfaces.rest.resources.ErrorResource;
import com.demy.platform.shared.interfaces.rest.transform.ErrorResourceFromExceptionAssembler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
        var path = extractPath(request);
        var message = localizationService.getMessage(ex.getDomainError().name(), ex.getArgs(), locale);
        LOGGER.warn("Domain exception: {} at {}", ex.getDomainError().name(), path, ex);
        var errorResource = ErrorResourceFromExceptionAssembler.toResourceFromException(
                ex.getDomainError().name(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), message, path);
        return new ResponseEntity<>(errorResource, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResource> handleIllegalArgumentException(IllegalArgumentException ex, Locale locale, WebRequest request) {
        var path = extractPath(request);
        var message = localizationService.getMessage("error.bad_request", null, locale);
        LOGGER.warn("Illegal argument exception at {}: {}", path, ex.getMessage(), ex);
        var errorResource = ErrorResourceFromExceptionAssembler.toResourceFromException(
                "BAD_REQUEST", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), message, path);
        return new ResponseEntity<>(errorResource, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResource> handleValidationException(MethodArgumentNotValidException ex, Locale locale, WebRequest request) {
        var path = extractPath(request);
        var message = ex.getBindingResult().getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .reduce((m1, m2) -> m1 + "; " + m2)
                .orElse(localizationService.getMessage("error.validation", null, locale));
        var errorResource = ErrorResourceFromExceptionAssembler.toResourceFromException(
                "BAD_REQUEST", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), message, path);
        return new ResponseEntity<>(errorResource, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NullPointerException.class, IllegalStateException.class})
    public ResponseEntity<ErrorResource> handleSpecificRuntimeExceptions(RuntimeException ex, Locale locale, WebRequest request) {
        var path = extractPath(request);
        var message = localizationService.getMessage("error.runtime", null, locale);
        LOGGER.error("Runtime exception at {}: {}", path, ex.getMessage(), ex);
        var errorResource = ErrorResourceFromExceptionAssembler.toResourceFromException(
                "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), message, path);
        return new ResponseEntity<>(errorResource, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle unexpected exceptions (system errors)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResource> handleGenericException(Exception ex, Locale locale, WebRequest request) {
        var path = extractPath(request);
        var message = localizationService.getMessage("error.unexpected", null, locale);
        LOGGER.error("Unexpected exception at {}: {}", path, ex.getMessage(), ex);
        var errorResource = ErrorResourceFromExceptionAssembler.toResourceFromException(
                "INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), message, path);
        return new ResponseEntity<>(errorResource, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String extractPath(WebRequest request) {
        return request.getDescription(false).replace("uri=", "");
    }
}