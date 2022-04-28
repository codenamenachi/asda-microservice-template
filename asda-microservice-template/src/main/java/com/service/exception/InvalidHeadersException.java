package com.service.exception;

public class InvalidHeadersException extends RuntimeException {

    public InvalidHeadersException(){
        super("Invalid headers received");
    }

    public InvalidHeadersException(String message){
        super(message);
    }
}
