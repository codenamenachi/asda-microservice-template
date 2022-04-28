package com.service.exception;

public class AmbiguousEmpIdException extends Exception {

    public AmbiguousEmpIdException() {
        super("Request has ambiguous employee ID");
    }

    public AmbiguousEmpIdException(String message) {
        super(message);
    }
}
