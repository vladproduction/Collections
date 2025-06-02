package com.vladproduction.ch07_generic.p06_generic_processor_system;

/**
 * Exception thrown during processing operations
 */
public class ProcessingException extends Exception {

    public ProcessingException(String message) {
        super(message);
    }

    public ProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
