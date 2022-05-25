package com.niine.quarkus.exception;

import java.io.Serializable;

public class ValidateException extends
        RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public ValidateException(String message) {
        super(message);
    }
}

