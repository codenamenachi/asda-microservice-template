package com.service.exception;

public class EmployeeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmployeeNotFoundException() {
        super("Requested employee not found");
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
