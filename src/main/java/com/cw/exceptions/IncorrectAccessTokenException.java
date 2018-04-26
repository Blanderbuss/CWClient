package com.cw.exceptions;

public class IncorrectAccessTokenException extends Exception {
    public IncorrectAccessTokenException(String message){
        super(message);
    }
}
