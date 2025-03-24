package org.example.exception;

public class LoginAlreadyTakenException extends RuntimeException{
    public LoginAlreadyTakenException(String message) {
        super(message);
    }
}
