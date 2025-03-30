package org.example.exception;

public class LoginNotFoundException extends RuntimeException{
    public LoginNotFoundException(String message) {
        super(message);
    }
}
