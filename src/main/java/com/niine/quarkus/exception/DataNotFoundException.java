package com.niine.quarkus.exception;

import java.io.Serializable;

public class DataNotFoundException extends
        RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public DataNotFoundException(String message) {
        super(message);
    }
}

