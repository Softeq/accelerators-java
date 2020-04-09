/*
 *
 *  * Developed by Softeq Development Corporation
 *  * http://www.softeq.com
 *
 */

package com.softeq.accelerator.flyway.controller.advice;

import com.softeq.accelerator.flyway.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Handles exception thrown from REST API
 * <p/>
 * Created on 4/7/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Slf4j
@ControllerAdvice(
    basePackages = {"com.softeq.accelerator.flyway.controller"},
    annotations = RestController.class
)
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        return handleInternal(ex, request, NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<String> handleException(Exception ex, WebRequest request) {
        return handleInternal(ex, request, INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<String> handleInternal(Exception ex, WebRequest request, HttpStatus status) {
        String description = request.getDescription(true);
        String msg = String.format("Failed to process request [%s].", description);
        if (log.isTraceEnabled()) {
            log.trace(msg + " Cause:", ex);
        } else if (log.isDebugEnabled()) {
            log.debug(msg + " Cause: {}", ex.toString());
        } else {
            log.warn(msg);
        }
        return new ResponseEntity<>(ex.getMessage(), status);
    }
}
