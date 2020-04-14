/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.exception;

/**
 * The exception is thrown when no entity found (handled and transformed to HTTP 404
 * <p/>
 * Created on 4/7/2020.
 * <p/>
 *
 * @author slapitsky
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
