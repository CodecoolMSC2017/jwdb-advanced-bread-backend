package com.codecool.bread.exception;

public class UsernameTakenException extends RuntimeException{
    public UsernameTakenException(String message) {
        super(message + "already taken. Choose another username");
    }
}
