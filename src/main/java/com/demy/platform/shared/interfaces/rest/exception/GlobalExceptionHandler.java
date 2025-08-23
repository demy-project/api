package com.demy.platform.shared.interfaces.rest.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.demy.platform.shared.domain.exceptions.DomainException;
import com.demy.platform.shared.domain.services.MessageResolver;
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
    private final MessageResolver messageResolver;

    public GlobalExceptionHandler(MessageResolver messageResolver) {
        this.messageResolver = messageResolver;
    }

    /**
     * Handle domain-specific exceptions (business rules)
     */
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResource> handleDomainException(DomainException ex, Locale locale, WebRequest request) {
        String message = messageResolver.getMessage(ex.getMessageCode(), ex.getArgs(), locale);
        String path = request.getDescription(false).replace("uri=", "");
        LOGGER.warn("Domain exception: {} at {}", ex.getMessageCode(), path, ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResource(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        "Bad Request",
                        ex.getErrorCode(),
                        message,
                        path
                ));
    }

    /**
     * Handle unexpected exceptions (system errors)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResource> handleGenericException(Exception ex, Locale locale, WebRequest request) {
        String message = messageResolver.getMessage("error.unexpected", null, locale);
        String path = request.getDescription(false).replace("uri=", "");
        LOGGER.error("Unexpected exception at {}: {}", path, ex.getMessage(), ex);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResource(
                        LocalDateTime.now(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Internal Server Error",
                        "error.unexpected",
                        message,
                        path
                ));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(
            RuntimeException ex, WebRequest request) {

        LOGGER.error("Runtime exception occurred: {}", ex.getMessage(), ex);

        Map<String, Object> errorResponse = createErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {

        LOGGER.error("Illegal argument exception occurred: {}", ex.getMessage(), ex);

        Map<String, Object> errorResponse = createErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private Map<String, Object> createErrorResponse(int status, String error, String message, String path) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now().toString());
        errorResponse.put("status", status);
        errorResponse.put("error", error);
        errorResponse.put("message", message);
        errorResponse.put("path", path);
        return errorResponse;
    }
}